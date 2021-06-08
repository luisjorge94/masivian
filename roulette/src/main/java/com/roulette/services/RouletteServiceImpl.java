package com.roulette.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.roulette.model.Bet;
import com.roulette.model.BetRequest;
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
	public List<Roulette> findAll() {
		List<Roulette> roulettes = new ArrayList<>();
		rouletteRepository.findAll().values().forEach(roulettes::add);
		return roulettes;
	}

	@Override
	public Roulette findById(String rouletteId) {
		return rouletteRepository.findById(rouletteId);
	}

	@Override
	public boolean update(Roulette roulette) {
		return rouletteRepository.update(roulette);
	}

	@Override
	public boolean addBet(BetRequest bet, Roulette roulette) {
		List<Bet> bets = new ArrayList<>();
		Bet betRequest = bet.getBet();
		betRequest.setId(UUID.randomUUID().toString());
		bets.add(bet.getBet());
		if(null == roulette.getBets())
			roulette.setBets(bets);
		else
			roulette.getBets().addAll(bets);
		return rouletteRepository.update(roulette);
	}

}
