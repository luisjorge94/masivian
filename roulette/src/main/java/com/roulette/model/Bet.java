package com.roulette.model;

import java.io.Serializable;

public class Bet implements Serializable {

    private String id;

    private BetTypeEnum type;

    private Integer betNumber;

    private ColorEnum color;

    private Double betValue;

    private Double valueWinning = 0D;

    private String userId;

    public Bet(){
    }

    public Bet(String id, BetTypeEnum type, Integer betNumber, ColorEnum color, Double betValue, Double valueWinning, String userId) {
        this.id = id;
        this.type = type;
        this.betNumber = betNumber;
        this.color = color;
        this.betValue = betValue;
        this.valueWinning = valueWinning;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BetTypeEnum getType() {
        return type;
    }

    public void setType(BetTypeEnum type) {
        this.type = type;
    }

    public Integer getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(Integer betNumber) {
        this.betNumber = betNumber;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public Double getBetValue() {
        return betValue;
    }

    public void setBetValue(Double betValue) {
        this.betValue = betValue;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getValueWinning() {
        return valueWinning;
    }

    public void setValueWinning(Double valueWinning) {
        this.valueWinning = valueWinning;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", betNumber=" + betNumber +
                ", color=" + color +
                ", betValue=" + betValue +
                ", userId=" + userId +
                ", valueWinning=" + valueWinning +
                '}';
    }
}
