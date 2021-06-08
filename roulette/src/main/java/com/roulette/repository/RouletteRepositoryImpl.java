package com.roulette.repository;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.roulette.model.Roulette;
import com.roulette.model.StatusEnum;

@Repository
public class RouletteRepositoryImpl implements RouletteRepository {

	private static final String KEY = "Roulette";
	static final Logger logger = Logger.getLogger(RouletteRepositoryImpl.class);
    
    @SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Roulette> findAll() {
		return redisTemplate.opsForHash().entries(KEY);
	}

	@Override
	public Roulette findById(String id) {
		System.out.println("Inicia Consulta por Id");
		return (Roulette) redisTemplate.opsForHash().get(KEY, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String save(Roulette roulette) {
		String rouletteId = "";
		try {
			rouletteId = UUID.randomUUID().toString();
			roulette.setId(rouletteId);
			roulette.setStatus(StatusEnum.CLOSE);
			redisTemplate.opsForHash().put(KEY, rouletteId, roulette);
		} catch (Exception ex) {
			rouletteId = "";
			System.out.println("Error al crear objeto Roulette: " + ex);
			logger.error("Error al crear objeto Roulette: " + ex);
		}

		return rouletteId;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Roulette roulette) {
		try {
			redisTemplate.opsForHash().put(KEY, roulette.getId(), roulette);
		} catch (Exception ex) {
			System.out.println("Error al actualizar Objeto Roulette: " + ex);
			logger.error("Error al actualizar Objeto Roulette: " + ex);
			return false;
		}

		return true;
	}
}
