package com.silentcloud.springrest.web.vo.misc;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SelectOption {
    String label;
    String value;
}
