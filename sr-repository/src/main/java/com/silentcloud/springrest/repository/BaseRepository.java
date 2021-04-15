package com.silentcloud.springrest.repository;


import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 公共基础 Repository接口
 *
 * @param <ID>     实体ID类型
 * @param <Entity> 实体类型
 * @author bianyun
 */
@NoRepositoryBean
public interface BaseRepository<ID extends Serializable, Entity extends Persistable<ID>>
        extends JpaRepository<Entity, ID>, JpaSpecificationExecutor<Entity> {

}
