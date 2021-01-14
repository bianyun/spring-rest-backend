package com.silentcloud.springrest.service.impl.module.sys;

import com.silentcloud.springrest.model.entity.sys.ApiPerm;
import com.silentcloud.springrest.repository.sys.ApiPermRepository;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.module.sys.ApiPermService;
import com.silentcloud.springrest.service.impl.mapper.sys.ApiPermMapper;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static com.silentcloud.springrest.jooq.gen.Tables.SYS_BUTTON_API_PERM;
import static com.silentcloud.springrest.jooq.gen.Tables.SYS_MENU_API_PERM;

@Service
@Transactional(readOnly = true)
public class ApiPermServiceImpl implements ApiPermService {
    private final DSLContext dsl;
    private final ApiPermRepository apiPermRepository;
    private final ApiPermMapper apiPermMapper;

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "RedundantSuppression"})
    @Autowired
    public ApiPermServiceImpl(DSLContext dsl,
                              ApiPermRepository apiPermRepository,
                              ApiPermMapper apiPermMapper) {
        this.dsl = dsl;
        this.apiPermRepository = apiPermRepository;
        this.apiPermMapper = apiPermMapper;
    }

    @Override
    public List<ApiPermDto> findAll() {
        return apiPermMapper.entityListToDtoList(apiPermRepository.findAll());
    }

    @Transactional
    @Override
    public void create(ApiPermDto dto) {
        ApiPerm savedEntity = apiPermRepository.save(apiPermMapper.dtoToEntity(dto));
        apiPermMapper.entityToDto(savedEntity);
    }

    @Transactional
    @Override
    public void updateById(Long id, ApiPermDto dto) {
        ApiPerm entity = apiPermRepository.getOne(id);
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        apiPermRepository.save(entity);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        apiPermRepository.deleteById(id);
    }

    @Override
    public ApiPermDto findByValue(String value) {
        return apiPermMapper.entityToDto(apiPermRepository.findByValue(value));
    }

    @Transactional
    @Override
    public void deleteApiPermsByValues(Collection<String> apiPermValues) {
        apiPermValues.forEach(value -> {
            ApiPerm apiPerm = apiPermRepository.findByValue(value);
            if (apiPerm != null) {
                Long apiPermId = apiPerm.getId();
                dsl.delete(SYS_BUTTON_API_PERM).where(SYS_BUTTON_API_PERM.API_PERM_ID.eq(apiPermId)).execute();
                dsl.delete(SYS_MENU_API_PERM).where(SYS_MENU_API_PERM.API_PERM_ID.eq(apiPermId)).execute();

                apiPermRepository.delete(apiPerm);
            }
        });
    }

}
