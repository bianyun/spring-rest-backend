package com.silentcloud.springrest.service.impl.module.lib;

import com.silentcloud.springrest.model.entity.lib.Translater;
import com.silentcloud.springrest.repository.lib.TranslaterRepository;
import com.silentcloud.springrest.service.api.dto.lib.TranslaterDto;
import com.silentcloud.springrest.service.api.module.lib.TranslaterService;
import com.silentcloud.springrest.service.impl.mapper.lib.TranslaterMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.silentcloud.springrest.jooq.gen.Tables.LIB_TRANSLATER;

@Service
@Transactional(readOnly = true)
public class TranslaterServiceImpl extends AbstractBaseService<Long, Translater, TranslaterDto> implements TranslaterService {
    private final TranslaterRepository translaterRepository;
    private final TranslaterMapper translaterMapper;

    @Autowired
    public TranslaterServiceImpl(DSLContext dsl,
                                 TranslaterRepository translaterRepository,
                                 TranslaterMapper translaterMapper) {
        super(dsl, translaterRepository, translaterMapper);
        this.translaterRepository = translaterRepository;
        this.translaterMapper = translaterMapper;
    }

    @Override
    protected Table<? extends Record> buildJoinedTable() {
        return LIB_TRANSLATER;
    }
}
