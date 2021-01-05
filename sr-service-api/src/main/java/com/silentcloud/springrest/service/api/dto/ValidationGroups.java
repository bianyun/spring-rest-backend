package com.silentcloud.springrest.service.api.dto;

import javax.validation.groups.Default;

public final class ValidationGroups {

    private ValidationGroups() {}

    public interface Create extends Default {}

    public interface Update extends Default {}

    public interface Reference {}

}
