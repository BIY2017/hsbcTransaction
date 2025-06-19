package com.hsbc.transaction.application.support;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

/**
 * TokenSupportAppService
 *
 * @author Lei
 * @date 2025/6/19 20:47
 */
@Service
public class TokenSupportAppService {

    @Autowired
    private RedissonClient redissonClient;

    public String generateIdempotent() {
        String token = "idemp:" + UUID.randomUUID();
        // 预存储在Redis中，标记为可用状态
        redissonClient.getBucket(token)
                .set("VALID", Duration.ofMinutes(30L));
        return token;
    }
}
