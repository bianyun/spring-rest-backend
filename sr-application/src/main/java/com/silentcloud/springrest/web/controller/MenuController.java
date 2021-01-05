package com.silentcloud.springrest.web.controller;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import com.silentcloud.springrest.service.api.module.sys.MenuService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/menus")
@Transactional
@RestController
public class MenuController {
    private final MenuService menuService;
    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public void test() {
        System.out.println("1=============================================");

        addRootMenuDto();
        // addRootMenu();
    }

    private void addRootMenuDto() {
        MenuDto menu_1 = buildMenuDto("menu_1", "menu_1");
        MenuDto menu_1_1 = buildMenuDto("menu_1_1", "menu_1_1");
        MenuDto menu_1_2 = buildMenuDto("menu_1_2", "menu_1_2");

        menu_1.addChild(menu_1_1);
        menu_1.addChild(menu_1_2);

        MenuDto savedMenu = menuService.create(menu_1);
        menu_1 = menuService.findById(savedMenu.getId());
        print("menu_1", menu_1);
    }

    private static MenuDto buildMenuDto(String name, String value) {
        MenuDto result = new MenuDto();
        result.setName(name);
        result.setValue(value);
        return result;
    }

    private void print(String header, Object obj) {
        try {
            System.out.println(StrUtil.format("{}: {}", header, OBJECT_MAPPER.writeValueAsString(obj)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
