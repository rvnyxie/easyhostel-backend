package com.easyhostel.backend.infrastructure.util.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Entry data seeder for Database
 *
 * @author Nyx
 */
@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final AccountSeeder _accountSeeder;
    private final RoleSeeder _roleSeeder;
    private final PermissionSeeder _permissionSeeder;
    private final RolePermissionSeeder _rolePermissionSeeder;
    private final DataSeeder _dataSeeder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        runSeeder();
    }

    /**
     * Run seeder methods
     *
     * @author Nyx
     */
    private void runSeeder() {
        _roleSeeder.init();
        _permissionSeeder.init();
        _rolePermissionSeeder.init();
        _accountSeeder.init();
        _dataSeeder.init();
    }

}
