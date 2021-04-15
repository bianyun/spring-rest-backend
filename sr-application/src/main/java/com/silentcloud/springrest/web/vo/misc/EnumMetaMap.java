package com.silentcloud.springrest.web.vo.misc;

import lombok.Builder;
import lombok.Value;

import java.util.HashMap;
import java.util.List;

/**
 * 枚举类元数据MAP
 *
 * @author bianyun
 */
public class EnumMetaMap extends HashMap<String, List<EnumMetaMap.Entry>> {
    @Builder
    @Value
    public static class Entry {
        String name;
        String label;
        Integer id;
    }

}


