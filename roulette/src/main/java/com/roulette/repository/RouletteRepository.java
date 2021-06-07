package com.roulette.repository;

import java.util.Map;

import com.roulette.model.Roulette;

public interface RouletteRepository {

	Map<String, Object> findAll();

	Object findById(String id);

	String save(Roulette roulette);

	void delete(String id);

	void update(Roulette roulette);
}
