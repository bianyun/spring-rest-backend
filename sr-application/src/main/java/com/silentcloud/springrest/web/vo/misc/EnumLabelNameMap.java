package com.silentcloud.springrest.web.vo.misc;

import lombok.Builder;
import lombok.Value;

import java.util.HashMap;
import java.util.List;

public class EnumLabelNameMap extends HashMap<String, List<EnumLabelNameMap.Entry>> {
    @Builder
    @Value
    public static class Entry {
        String label;
        String name;
    }

}


