package com.silentcloud.springrest.service.impl.module.lib;

import com.silentcloud.springrest.model.entity.lib.Translater;
import com.silentcloud.springrest.repository.lib.TranslaterRepository;
import com.silentcloud.springrest.service.api.dto.lib.TranslaterDto;
import com.silentcloud.springrest.service.api.module.lib.TranslaterService;
import com.silentcloud.springrest.service.impl.mapper.lib.TranslaterMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TranslaterServiceImpl extends AbstractBaseService<Long, Translater, TranslaterDto> implements TranslaterService {
    private final TranslaterRepository translaterRepository;
    private final TranslaterMapper translaterMapper;

    @Autowired
    public TranslaterServiceImpl(TranslaterRepository translaterRepository,
                                 TranslaterMapper translaterMapper) {
        super(translaterRepository, translaterMapper);
        this.translaterRepository = translaterRepository;
        this.translaterMapper = translaterMapper;
    }

}
