package com.roulette.model;

public class BetRequest {

    private String rouletteId;

    private Bet bet;

    public BetRequest() {
    }

    public BetRequest(String rouletteId, Bet bet) {
        this.rouletteId = rouletteId;
        this.bet = bet;
    }

    public String getRouletteId() {
        return rouletteId;
    }

    public void setRouletteId(String rouletteId) {
        this.rouletteId = rouletteId;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    @Override
    public String toString() {
        return "BetRequest{" +
                "rouletteId='" + rouletteId + '\'' +
                ", bet=" + bet +
                '}';
    }
}
