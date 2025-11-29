package com.korber.inventory_service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class InventoryFactory {

    @Autowired
    @Qualifier("defaultInventoryHandler")
    private InventoryHandler defaultHandler;

    public InventoryHandler getHandler(String type) {
        // Currently always returns default handler. In the future, different types can be added
        return defaultHandler;
    }
}
