package com.silentcloud.springrest.service.impl.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * 缓存配置
 *
 * @author bianyun
 */
@Configuration
@EnableCaching
public class CachingConfig {
    public final static String RUNTIME_CACHE_RESOLVER = "runtimeCacheResolver";

    @Bean(RUNTIME_CACHE_RESOLVER)
    public CacheResolver cacheResolver(CacheManager cacheManager) {
        return new RuntimeCacheResolver(cacheManager);
    }

    static class RuntimeCacheResolver extends SimpleCacheResolver {

        public RuntimeCacheResolver(CacheManager cacheManager) {
            super(cacheManager);
        }

        @Override
        protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
            return Collections.singletonList(ClassUtils.getUserClass(context.getTarget().getClass()).getName());
        }
    }
}
