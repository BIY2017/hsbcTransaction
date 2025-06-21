package com.hsbc.transaction.application.support;

import com.hsbc.transaction.domian.support.constant.CommonConstants;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private static final String IDEMP_TOKEN_PREFIX = "idemp:";

    @Value("${custom.idemp-token.expire-time}")
    private Long expireTime;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 生成防重复提交token
     *
     * @return 防重复提交token
     */
    public String generateIdempotent() {
        String token = IDEMP_TOKEN_PREFIX + UUID.randomUUID();
        redissonClient.getBucket(token)
                .set(CommonConstants.REDISSON_VALID, Duration.ofMinutes(expireTime));
        return token;
    }
}
