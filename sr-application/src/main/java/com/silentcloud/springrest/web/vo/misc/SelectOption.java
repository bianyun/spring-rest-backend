package com.silentcloud.springrest.web.vo.misc;

import lombok.Builder;
import lombok.Value;

/**
 * 选择框选项VO
 *
 * @author bianyun
 */
@Value
@Builder
public class SelectOption {
    String label;
    String value;
}
