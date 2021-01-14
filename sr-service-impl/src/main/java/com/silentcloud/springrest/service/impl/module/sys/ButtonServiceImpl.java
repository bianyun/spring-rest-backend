package com.silentcloud.springrest.service.impl.module.sys;

import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.model.entity.sys.Menu;
import com.silentcloud.springrest.repository.sys.ButtonRepository;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.module.sys.ButtonService;
import com.silentcloud.springrest.service.impl.mapper.sys.ApiPermMapper;
import com.silentcloud.springrest.service.impl.mapper.sys.ButtonMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.silentcloud.springrest.jooq.gen.Tables.SYS_BUTTON;

@Service
@Transactional(readOnly = true)
public class ButtonServiceImpl extends AbstractBaseService<Long, Button, ButtonDto> implements ButtonService {
    private final ButtonRepository buttonRepository;
    private final ButtonMapper buttonMapper;
    private final ApiPermMapper apiPermMapper;

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "RedundantSuppression"})
    @Autowired
    public ButtonServiceImpl(DSLContext dsl,
                             ButtonRepository buttonRepository,
                             ButtonMapper buttonMapper,
                             ApiPermMapper apiPermMapper) {
        super(dsl, buttonRepository, buttonMapper);
        this.buttonRepository = buttonRepository;
        this.buttonMapper = buttonMapper;
        this.apiPermMapper = apiPermMapper;
    }

    @Override
    public Set<ApiPermDto> getApiPermsByButtonId(Long buttonId) {
        Button button = buttonRepository.getOne(buttonId);
        return apiPermMapper.entitySetToDtoSet(button.getApiPerms());
    }

    @Override
    public Map<String, List<ButtonDto>> getMenuToButtonPermsMap() {
        Map<String, List<ButtonDto>> resultMap = new HashMap<>();

        List<Button> buttons = buttonRepository.findAllByOrderByShowOrder();
        buttons.forEach(button -> {
            Menu parentMenu = button.getParentMenu();
            List<ButtonDto> buttonDtos = resultMap.computeIfAbsent(parentMenu.getValue(), k -> new ArrayList<>());
            buttonDtos.add(buttonMapper.entityToDto(button));
        });

        return resultMap;
    }

    @Override
    protected Table<? extends Record> buildJoinedTable() {
        return SYS_BUTTON;
    }
}
