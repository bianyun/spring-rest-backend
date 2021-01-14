package com.silentcloud.springrest.service.api.dto;

import com.silentcloud.springrest.service.api.dto.ValidationGroups.Create;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Reference;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class BaseDto<ID extends Serializable, Entity extends Persistable<ID>> {

    @Null(groups = Create.class)
    @NotNull(groups = {Update.class, Reference.class})
    @ApiModelProperty(value = "ID")
    private ID id;

}
