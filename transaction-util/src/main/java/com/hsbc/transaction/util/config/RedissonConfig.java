package com.hsbc.transaction.util.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisConfig
 *
 * @author Lei
 * @date 2025/6/19 19:13
 */

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        config.useSingleServer()
                .setAddress("redis://localhost:6379")
                .setConnectionPoolSize(32)
                .setConnectionMinimumIdleSize(8)
                .setIdleConnectionTimeout(10000)
                .setConnectTimeout(5000)
                .setTimeout(3000);

        return Redisson.create(config);
    }
}
