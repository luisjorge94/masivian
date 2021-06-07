package com.roulette.controllers;

import java.util.ArrayList;
import java.util.List;

import com.roulette.model.OpenRequest;
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
	public ResponseEntity<String> getRoulette() {
		String rouletteId = this.rouletteService.saveRoulette(new Roulette());
		return new ResponseEntity<String>(rouletteId, HttpStatus.OK);
	}
	
	@GetMapping("/allRoulettes")
	public ResponseEntity<List<Object>> getAll() {
		return new ResponseEntity<List<Object>>(this.rouletteService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/openRoulette")
	public ResponseEntity<String> openRoulette(@RequestBody OpenRequest request) {

		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
