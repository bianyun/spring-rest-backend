package com.silentcloud.springrest.repository.sys;

import com.silentcloud.springrest.repository.BaseRepository;
import com.silentcloud.springrest.model.entity.sys.Menu;

import java.util.List;

public interface MenuRepository extends BaseRepository<Long, Menu> {

    List<Menu> findAllByOrderByValue();

    Menu findByValue(String value);

}
