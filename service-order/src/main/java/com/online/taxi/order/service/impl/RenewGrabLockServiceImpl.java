package com.online.taxi.order.service.impl;

import com.online.taxi.order.service.RenewGrabLockService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 这种锁续期，还是有并发的问题
 *
 * @author yueyi2019
 */
@Service
public class RenewGrabLockServiceImpl implements RenewGrabLockService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    @Async
    public void renewLock(String key, String value, int time) {
        System.out.println("续命" + key + " " + value);
        String v = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(v) && v.equals(value)) {
            int sleepTime = time / 3;
            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            renewLock(key, value, time);
        }
    }
}
