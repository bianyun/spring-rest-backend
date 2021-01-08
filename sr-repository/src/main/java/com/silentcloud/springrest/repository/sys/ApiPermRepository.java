package com.silentcloud.springrest.repository.sys;

import com.silentcloud.springrest.repository.BaseRepository;
import com.silentcloud.springrest.model.entity.sys.ApiPerm;

public interface ApiPermRepository extends BaseRepository<Long, ApiPerm> {

    ApiPerm findByValue(String value);

}
