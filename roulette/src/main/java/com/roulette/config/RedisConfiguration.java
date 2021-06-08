package com.roulette.config;

import com.roulette.model.Roulette;
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
    RedisTemplate<String, Roulette> RedisTemplate(){
    	RedisTemplate<String, Roulette> redisTemplate = new RedisTemplate<>();
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
