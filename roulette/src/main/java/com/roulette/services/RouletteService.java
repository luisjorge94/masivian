package com.roulette.services;

import java.util.List;

import com.roulette.model.BetRequest;
import com.roulette.model.Roulette;

public interface RouletteService {

	String saveRoulette(Roulette roulette);

	List<Roulette> findAll();

	Roulette findById(String rouletteId);

	boolean update(Roulette roulette);

	boolean addBet(BetRequest bet, Roulette roulette);
}
