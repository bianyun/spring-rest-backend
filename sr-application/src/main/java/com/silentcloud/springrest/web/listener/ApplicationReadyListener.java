package com.silentcloud.springrest.web.listener;

import com.silentcloud.springrest.service.impl.meta.EntityRepositoryMap;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyListener {

    @SuppressWarnings("unused")
    @Async
    @EventListener
    public void handleContextStart(ApplicationReadyEvent event) {
        EntityRepositoryMap.initialize();
    }

}
