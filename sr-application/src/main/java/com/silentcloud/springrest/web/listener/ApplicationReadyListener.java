package com.silentcloud.springrest.web.listener;

import com.silentcloud.springrest.service.impl.meta.EntityRepositoryMap;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 应用启动完成事件监听器（用于初始化 EntityRepositoryMap）
 *
 * @author bianyun
 */
@Component
public class ApplicationReadyListener {

    @SuppressWarnings("unused")
    @Async
    @EventListener
    public void handleContextStart(ApplicationReadyEvent event) {
        EntityRepositoryMap.initialize();
    }

}
