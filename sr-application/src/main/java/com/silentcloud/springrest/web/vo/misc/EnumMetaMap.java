package com.silentcloud.springrest.web.vo.misc;

import lombok.Builder;
import lombok.Value;

import java.util.HashMap;
import java.util.List;

public class EnumMetaMap extends HashMap<String, List<EnumMetaMap.Entry>> {
    @Builder
    @Value
    public static class Entry {
        String name;
        String label;
        Integer id;
    }

}


