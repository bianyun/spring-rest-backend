package com.silentcloud.springrest.service.impl.meta;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import com.silentcloud.springrest.repository.BaseRepository;
import com.silentcloud.springrest.service.impl.util.BeanUtil;
import com.silentcloud.springrest.util.MiscUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@SuppressWarnings("unchecked")
public final class EntityRepositoryMap {
    private static final Map<Class<? extends Persistable<?>>,
            BaseRepository<?, ? extends Persistable<?>>> REPOSITORY_MAP = new HashMap<>();

    private EntityRepositoryMap() {
        throw new AssertionError("deliberately prohibit instantiation");
    }

    public static void initialize() {
        if (!REPOSITORY_MAP.isEmpty()) {
            return;
        }

        log.info("EntityRepositoryMap initialization start...");

        String baseRepositoryPackage = ClassUtil.getPackage(BaseRepository.class);
        Set<Class<?>> repositoryClasses = ClassUtil.scanPackageBySuper(baseRepositoryPackage, BaseRepository.class);

        repositoryClasses.forEach(repoClass -> {
            Class<? extends Persistable<?>> entityClass = MiscUtil.getEntityGenericParameterClass(repoClass);
            BaseRepository<?, ? extends Persistable<?>> repositoryBean =
                    BeanUtil.getBean((Class<? extends BaseRepository<?, ? extends Persistable<?>>>)repoClass);
            REPOSITORY_MAP.put(entityClass, repositoryBean);
        });

        log.info("EntityRepositoryMap initialization finished, size={}", REPOSITORY_MAP.size());
    }

    public static <ID extends Serializable, T extends Persistable<ID>, R extends BaseRepository<ID, T>> R
    getRepository(Class<T> entityClass) {
        Assert.notEmpty(REPOSITORY_MAP);
        return (R) REPOSITORY_MAP.get(entityClass);
    }
}
