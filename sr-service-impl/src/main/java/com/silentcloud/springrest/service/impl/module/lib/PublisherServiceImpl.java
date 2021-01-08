package com.silentcloud.springrest.service.impl.module.lib;

import com.silentcloud.springrest.model.entity.lib.Publisher;
import com.silentcloud.springrest.repository.lib.PublisherRepository;
import com.silentcloud.springrest.service.api.dto.lib.PublisherDto;
import com.silentcloud.springrest.service.api.module.lib.PublisherService;
import com.silentcloud.springrest.service.impl.mapper.lib.PublisherMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.silentcloud.springrest.jooq.gen.Tables.LIB_PUBLISHER;

@Service
@Transactional(readOnly = true)
public class PublisherServiceImpl extends AbstractBaseService<Long, Publisher, PublisherDto> implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Autowired
    public PublisherServiceImpl(DSLContext dsl,
                                PublisherRepository publisherRepository,
                                PublisherMapper publisherMapper) {
        super(dsl, publisherRepository, publisherMapper);
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @Override
    protected Table<? extends Record> buildJoinedTable() {
        return LIB_PUBLISHER;
    }
}
