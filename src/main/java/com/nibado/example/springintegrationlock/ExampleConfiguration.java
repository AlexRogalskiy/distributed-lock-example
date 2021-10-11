package com.nibado.example.springintegrationlock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.support.locks.LockRegistry;

import javax.sql.DataSource;

@Configuration
public class ExampleConfiguration {
    @Bean
    public DefaultLockRepository defaultLockRepository(DataSource dataSource) {
        return new DefaultLockRepository(dataSource);
    }

    @Bean
    public LockRegistry lockRegistry (DefaultLockRepository lockRepository) {
        return new JdbcLockRegistry(lockRepository);
    }
}
