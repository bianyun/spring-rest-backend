package com.silentcloud.springrest.service.impl.module.sys;

import com.silentcloud.springrest.model.entity.sys.ApiPerm;
import com.silentcloud.springrest.repository.sys.ApiPermRepository;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.module.sys.ApiPermService;
import com.silentcloud.springrest.service.impl.mapper.sys.ApiPermMapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class ApiPermServiceImpl implements ApiPermService {
    private final ApiPermRepository apiPermRepository;
    private final ApiPermMapper apiPermMapper;

    @Autowired
    public ApiPermServiceImpl(ApiPermRepository apiPermRepository,
                              ApiPermMapper apiPermMapper) {
        this.apiPermRepository = apiPermRepository;
        this.apiPermMapper = apiPermMapper;
    }

    @Override
    public List<ApiPermDto> findAll() {
        return apiPermMapper.entityListToDtoList(apiPermRepository.findAll());
    }

    @Transactional
    @Override
    public ApiPermDto create(@NonNull ApiPermDto dto) {
        ApiPerm savedEntity = apiPermRepository.save(apiPermMapper.dtoToEntity(dto));
        return apiPermMapper.entityToDto(savedEntity);
    }

    @Transactional
    @Override
    public void updateById(@NonNull Long id, @NonNull ApiPermDto dto) {
        ApiPerm entity = apiPermRepository.getOne(id);
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        apiPermRepository.save(entity);
    }

    @Transactional
    @Override
    public void deleteById(@NonNull Long id) {
        apiPermRepository.deleteById(id);
    }

    @Override
    public ApiPermDto findById(@NonNull Long id) {
        return apiPermMapper.entityToDto(apiPermRepository.getOne(id));
    }

    @Override
    public ApiPermDto findByValue(@NonNull String value) {
        return apiPermMapper.entityToDto(apiPermRepository.findByValue(value));
    }
}
