package com.roulette.repository;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.roulette.model.Roulette;
import com.roulette.model.StatusEnum;

@Repository
public class RouletteRepositoryImpl implements RouletteRepository {

private static final String KEY = "Roulette";
    
    @SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findAll() {
		return redisTemplate.opsForHash().entries(KEY);
	}

	@Override
	public Object findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String save(Roulette roulette) {
		String rouletteId = UUID.randomUUID().toString();
		roulette.setId(rouletteId);
		roulette.setStatus(StatusEnum.CLOSE);
		
		redisTemplate.opsForHash().put(KEY, rouletteId, roulette);
		
		return rouletteId;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Roulette roulette) {
		redisTemplate.opsForHash().put(KEY, roulette.getId(), roulette);
	}
}
