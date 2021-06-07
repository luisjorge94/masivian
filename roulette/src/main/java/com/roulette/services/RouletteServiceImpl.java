package com.roulette.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roulette.model.Roulette;
import com.roulette.repository.RouletteRepository;

@Service
public class RouletteServiceImpl implements RouletteService {

	@Autowired
	private RouletteRepository rouletteRepository;
	
	@Override
	public String saveRoulette(Roulette roulette) {
		return rouletteRepository.save(roulette);
	}

	@Override
	public List<Object> findAll() {
		List<Object> roulettes = new ArrayList<>();
		rouletteRepository.findAll().values().forEach(roulettes::add);
		return roulettes;
	}

}
