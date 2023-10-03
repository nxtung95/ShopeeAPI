package com.sasaug.monkeemods.services.shopee.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
public class JedisHelper {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void set(String key, String data, long expireSeconds) {
		redisTemplate.opsForValue().set(key, data, Duration.ofSeconds(expireSeconds));
	}

	public void set(String key, String data) {
		redisTemplate.opsForValue().set(key, data);
	}

	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public boolean del(String key) {
		return redisTemplate.delete(key);
	}

	public boolean isCached(String key) {
		return redisTemplate.hasKey(key);
	}
}
