package com.silentcloud.springrest.web.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Create;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Update;
import com.silentcloud.springrest.service.api.dto.sys.UserDto;
import com.silentcloud.springrest.service.api.module.BaseService;
import com.silentcloud.springrest.service.api.query.FlatQueryService;
import com.silentcloud.springrest.service.api.query.JpaQueryService;
import com.silentcloud.springrest.service.api.query.request.PageQueryParam;
import com.silentcloud.springrest.service.api.query.request.QueryParam;
import com.silentcloud.springrest.service.api.query.response.FlatQueryRecord;
import com.silentcloud.springrest.service.api.query.response.PageInfo;
import com.silentcloud.springrest.util.LabelUtil;
import com.silentcloud.springrest.util.MiscUtil;
import com.silentcloud.springrest.web.ActionTargetResourceNotFoundException;
import com.silentcloud.springrest.web.IllegalWebOperationException;
import com.silentcloud.springrest.web.ResourceNotFoundException;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPerm;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPermViewDetail;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPermViewList;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Persistable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static com.silentcloud.springrest.web.util.Consts.API_PERM_PREFIX;
import static com.silentcloud.springrest.web.util.Consts.PLACEHOLDER_LABEL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Validated
public abstract class AbstractBaseController<ID extends Serializable,
        Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>> {
    protected final Class<Entity> entityClass = MiscUtil.getEntityGenericParameterClass(getClass());
    private final String label = LabelUtil.getClassLabel(MiscUtil.getDtoGenericParameterClass(getClass()));

    private final JpaQueryService jpaQueryService;
    private final FlatQueryService flatQueryService;
    private final BaseService<ID, Entity, DTO> service;

    protected AbstractBaseController(JpaQueryService jpaQueryService,
                                     FlatQueryService flatQueryService,
                                     BaseService<ID, Entity, DTO> service) {
        this.jpaQueryService = jpaQueryService;
        this.flatQueryService = flatQueryService;
        this.service = service;
    }

    @ApiOperationSupport(order = 1)
    @RequiresPermViewList
    @ApiOperation("获取所有列表")
    @GetMapping
    public List<DTO> getAll() {
        return service.findAll();
    }

    @ApiOperationSupport(order = 2)
    @RequiresPermViewDetail
    @ApiOperation("通过ID查找")
    @GetMapping("/{id}")
    public DTO readById(@PathVariable ID id) {
        DTO dto = service.findById(id);
        if (dto == null) {
            throw resourceNotFoundException(id);
        }
        return dto;
    }

    @ApiOperationSupport(order = 3)
    @RequiresPerm(name = "添加" + PLACEHOLDER_LABEL, value = API_PERM_PREFIX + "add")
    @ApiOperation("新增")
    @ResponseStatus(CREATED)
    @PostMapping
    public DTO create(@Validated(Create.class) @RequestBody DTO dto) {
        return service.create(dto);
    }

    @ApiOperationSupport(order = 4)
    @RequiresPerm(name = "更新" + PLACEHOLDER_LABEL, value = API_PERM_PREFIX + "update")
    @ApiOperation("更新")
    @PutMapping("/{id}")
    public DTO updateById(@PathVariable ID id, @Validated(Update.class) @RequestBody DTO dto) {
        if (service.existsById(id)) {
            return service.updateById(id, dto);
        } else {
            throw actionTargetResourceNotFoundException(id, "更新");
        }
    }

    @ApiOperationSupport(order = 5)
    @RequiresPerm(name = "删除" + PLACEHOLDER_LABEL, value = API_PERM_PREFIX + "delete")
    @ApiOperation("删除")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ID id) {
        if (service.existsById(id)) {
            if (User.class.equals(entityClass)) {
                if (getCurrentUserId().equals(id)) {
                    throw new IllegalWebOperationException("不能删除当前登录用户自己的用户账号");
                }
            }
            service.deleteById(id);
        } else {
            throw actionTargetResourceNotFoundException(id, "删除");
        }
    }

    @ApiOperationSupport(order = 6)
    @RequiresPerm(name = "批量删除" + PLACEHOLDER_LABEL, value = API_PERM_PREFIX + "batch-delete")
    @ApiOperation("批量删除")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/batch")
    public void batchDeleteByIdSet(@RequestBody Set<ID> idSet) {
        service.batchDeleteByIdSet(idSet);
    }

    @RequiresPermViewList
    @ApiOperationSupport(order = 7)
    @ApiOperation("JPA查询")
    @GetMapping("/query-jpa")
    public List<DTO> queryJpa(QueryParam queryParam) {
        return jpaQueryService.query(entityClass, queryParam);
    }

    @RequiresPermViewList
    @ApiOperationSupport(order = 8)
    @ApiOperation("扁平查询")
    @GetMapping("/query-flat")
    public List<FlatQueryRecord> queryFlat(QueryParam queryParam) {
        return flatQueryService.query(entityClass, queryParam);
    }

    @RequiresPermViewList
    @ApiOperationSupport(order = 9)
    @ApiOperation("分页JPA查询")
    @GetMapping("/page-query-jpa")
    public PageInfo<DTO> pageQueryJpa(PageQueryParam pageQueryParam) {
        return jpaQueryService.pageQuery(entityClass, pageQueryParam);
    }

    @RequiresPermViewList
    @ApiOperationSupport(order = 10)
    @ApiOperation("分页扁平查询")
    @GetMapping("/page-query-flat")
    public PageInfo<FlatQueryRecord> pageQueryFlat(PageQueryParam pageQueryParam) {
        return flatQueryService.pageQuery(entityClass, pageQueryParam);
    }

    protected static String getCurrentUsername() {
        Subject subject = SecurityUtils.getSubject();
        return ((UserDto) subject.getPrincipal()).getUsername();
    }

    protected static Long getCurrentUserId() {
        Subject subject = SecurityUtils.getSubject();
        return ((UserDto) subject.getPrincipal()).getId();
    }

    protected ActionTargetResourceNotFoundException actionTargetResourceNotFoundException(ID id, String actionLabel) {
        return new ActionTargetResourceNotFoundException(id, label, actionLabel);
    }

    protected ResourceNotFoundException resourceNotFoundException(ID id) {
        return new ResourceNotFoundException(id, label);
    }
}
