package com.silentcloud.springrest.repository.sys;

import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.repository.BaseRepository;

import java.util.List;

public interface ButtonRepository extends BaseRepository<Long, Button> {

    List<Button> findAllByOrderByShowOrder();

}
