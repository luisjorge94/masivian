package com.roulette.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.roulette.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		boolean isUpdated = false;
		Roulette roulette = this.rouletteService.findById(rouletteId);

		if (null != roulette) {
			roulette.setStatus(StatusEnum.OPEN);
			isUpdated = this.rouletteService.update(roulette);
		}

		return new ResponseEntity<String>(isUpdated ? "Sucessfull Operation" : "Denegate Operation", HttpStatus.OK);
	}

	@PostMapping("/bet")
	public ResponseEntity<String> createBet(@RequestBody BetRequest bet, @RequestHeader("user-id") String userId){
		String response = "";

		System.out.println(bet.toString());
		System.out.println("UserId: " +  userId);
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

	@GetMapping("/closeRoulette/{rouletteId}")
	public ResponseEntity<BetWinner> closeRoulette(@PathVariable String rouletteId) {
		Roulette roulette = this.rouletteService.findById(rouletteId);

		BetWinner winners = new BetWinner();

		if (null != roulette) {
			roulette.setStatus(StatusEnum.CLOSE);

			// Winner number
			int numberWinner = (int) (Math.random() * 36);

			List<Bet> colorBets = new ArrayList<>();

			winners.setNumberWinner(numberWinner);

			for (Bet bet : roulette.getBets()) {
				if (bet.getType().equals(BetTypeEnum.NUMBER) && (int) bet.getBetNumber() == numberWinner)
					bet.setValueWinning(bet.getBetValue() * 5);
				else if (bet.getType().equals(BetTypeEnum.COLOR) && (bet.getBetNumber() % 2 == 0 && bet.getColor().equals(ColorEnum.RED)) && numberWinner % 2 == 0)
					bet.setValueWinning(bet.getBetValue() * 1.8);
				else if (bet.getType().equals(BetTypeEnum.COLOR) && (bet.getBetNumber() % 2 != 0 && bet.getColor().equals(ColorEnum.BLACK)) && numberWinner % 2 != 0)
					bet.setValueWinning(bet.getBetValue() * 1.8);
				else
					bet.setValueWinning(0D);
			}

			List<Bet> bets = roulette.getBets().stream().filter(b -> b.getValueWinning() > 0D).collect(Collectors.toList());

			winners.setBetWinners(bets);
		}

		return new ResponseEntity<BetWinner>(winners, HttpStatus.OK);
	}
}
