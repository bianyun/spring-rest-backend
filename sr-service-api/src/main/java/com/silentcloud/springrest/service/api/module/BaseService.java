package com.silentcloud.springrest.service.api.module;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>> {

    boolean existsById(ID id);

    @Nullable
    DTO findById(ID id);

    List<DTO> findAll();

    DTO create(DTO dto);

    DTO updateById(ID id, DTO dto);

    void deleteById(ID id);

    void activateById(ID id);

    void deactivateById(ID id);

}
