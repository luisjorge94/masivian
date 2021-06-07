package com.roulette.services;

import java.util.List;

import com.roulette.model.Roulette;

public interface RouletteService {

	String saveRoulette(Roulette roulette);

	List<Object> findAll();

}
