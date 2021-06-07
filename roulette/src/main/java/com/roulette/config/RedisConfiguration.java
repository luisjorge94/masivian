package com.roulette.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
    @Bean
    RedisTemplate<String, Object> RedisTemplate(){
    	RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    	redisTemplate.setConnectionFactory(jedisConnectionFactory());
    	redisTemplate.setKeySerializer(new StringRedisSerializer());
    	redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    	redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
    	redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
    	redisTemplate.setEnableTransactionSupport(true);
    	redisTemplate.afterPropertiesSet();
    	return redisTemplate;
    }
}