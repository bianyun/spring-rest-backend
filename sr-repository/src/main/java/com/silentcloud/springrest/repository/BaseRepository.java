package com.silentcloud.springrest.repository;


import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<ID extends Serializable, Entity extends Persistable<ID>>
        extends JpaRepository<Entity, ID>, JpaSpecificationExecutor<Entity> {

}
