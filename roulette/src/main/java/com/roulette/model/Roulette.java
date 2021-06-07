package com.roulette.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Roulette")
public class Roulette implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Id;
	private StatusEnum Status;
	private List<Bet> bets;

	public Roulette(String id, StatusEnum status, List<Bet> bets) {
		Id = id;
		Status = status;
		this.bets = bets;
	}

	public Roulette() {
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public StatusEnum getStatus() {
		return Status;
	}

	public void setStatus(StatusEnum status) {
		Status = status;
	}

	public List<Bet> getBets() {
		return bets;
	}

	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}

}
