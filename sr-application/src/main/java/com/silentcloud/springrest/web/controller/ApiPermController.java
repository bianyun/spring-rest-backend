package com.silentcloud.springrest.web.controller;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.module.sys.ApiPermService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api-perms")
@RestController
public class ApiPermController {
    private final ApiPermService apiPermService;
    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public ApiPermController(ApiPermService apiPermService) {
        this.apiPermService = apiPermService;
    }

    @GetMapping
    public void test() {
        System.out.println("1=============================================");

        addRootApiPermDto();
    }

    private void addRootApiPermDto() {
        ApiPermDto perm_1 = buildApiPermDto("api_perm_1", "api_perm_1");
        ApiPermDto perm_1_1 = buildApiPermDto("api_perm_1_1", "api_perm_1_1");
        ApiPermDto perm_1_2 = buildApiPermDto("api_perm_1_2", "api_perm_1_2");

        perm_1.addChild(perm_1_1);
        perm_1.addChild(perm_1_2);

        ApiPermDto savedApiPerm = apiPermService.create(perm_1);
        perm_1 = apiPermService.findById(savedApiPerm.getId());
        print("perm_1", perm_1);
    }

    private static ApiPermDto buildApiPermDto(String name, String value) {
        ApiPermDto result = new ApiPermDto();
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
