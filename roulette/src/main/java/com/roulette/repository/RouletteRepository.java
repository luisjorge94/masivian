package com.roulette.repository;

import java.util.Map;

import com.roulette.model.Roulette;

public interface RouletteRepository {

	Map<String, Roulette> findAll();

	Roulette findById(String id);

	String save(Roulette roulette);

	void delete(String id);

	boolean update(Roulette roulette);
}
