package com.roulette.controllers;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roulette.model.BetRequest;
import com.roulette.model.OpenRequest;
import com.roulette.model.StatusEnum;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.roulette.model.Roulette;
import com.roulette.services.RouletteService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/roulette")
public class RouletteController {
	
	@Autowired
	private RouletteService rouletteService;


	@GetMapping("/new")
	public ResponseEntity<String> saveRoulette() {
		String rouletteId = this.rouletteService.saveRoulette(new Roulette());
		return new ResponseEntity<String>(rouletteId, HttpStatus.OK);
	}
	
	@GetMapping("/allRoulettes")
	public ResponseEntity<List<Roulette>> getAll() {
		return new ResponseEntity<List<Roulette>>(this.rouletteService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/openRoulette/{rouletteId}")
	public ResponseEntity<String> openRoulette(@PathVariable String rouletteId) {
		Roulette roulette = this.rouletteService.findById(rouletteId);
		roulette.setStatus(StatusEnum.OPEN);

		return new ResponseEntity<String>(this.rouletteService.update(roulette) ? "Sucessfull Operation" : "Denegate Operation", HttpStatus.OK);
	}

	@PostMapping("/bet")
	public ResponseEntity<String> createBet(@RequestBody BetRequest bet, @RequestHeader("user-id") String userId){
		String response = "";

		System.out.println(bet.toString());
		if (null == bet && bet.getBet() == null)
			response = "Debe ingresar una apuesta";
		else if (bet.getRouletteId() == null && bet.getRouletteId() == "")
			response = "Debe ingresar el identificador de la ruleta";
		else if (bet.getBet().getBetNumber() < 0 || bet.getBet().getBetNumber() > 36)
			response = "Debe ingresar un número válido entre 0 y 36";
		else if (bet.getBet().getBetValue() < 0D || bet.getBet().getBetValue() > 10000D)
			response = "El valor de la apuesta debe ser mayor a 0 y menor a 10.000 dólares";
		else {
			Roulette roulette = this.rouletteService.findById(bet.getRouletteId());
			bet.getBet().setUserId(userId);

			if (null == roulette || roulette.getStatus() == StatusEnum.CLOSE)
				response = "No existe la ruleta con Id: " + bet.getRouletteId() + " o su estado es CLOSE";
			else
				response = this.rouletteService.addBet(bet,roulette) ? "Sucessfull Bet" : "Denegate Bet";
		}

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
