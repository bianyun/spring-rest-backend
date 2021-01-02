package com.silentcloud.springrest.service.api.dto.sys;

import com.silentcloud.springrest.model.entity.sys.Menu;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("菜单")
public class MenuDto extends BaseDto<Long, Menu> {

    private String name;

    private String value;

    private MenuDto parent;

    private List<MenuDto> children = new ArrayList<>();

    public void addChild(MenuDto child) {
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(MenuDto child) {
        children.remove(child);
        child.setParent(null);
    }
}
