package com.roulette.model;

import java.io.Serializable;
import java.util.List;

public class BetWinner {

    private List<Bet> betWinners;

    private Number numberWinner;

    public BetWinner() {
    }

    public BetWinner(List<Bet> betWinners, Number numberWinner) {
        this.betWinners = betWinners;
        this.numberWinner = numberWinner;
    }

    public List<Bet> getBetWinners() {
        return betWinners;
    }

    public void setBetWinners(List<Bet> betWinners) {
        this.betWinners = betWinners;
    }

    public Number getNumberWinner() {
        return numberWinner;
    }

    public void setNumberWinner(Number numberWinner) {
        this.numberWinner = numberWinner;
    }
}
