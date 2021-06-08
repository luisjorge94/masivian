package com.roulette.model;

public enum ColorEnum {
    RED("RED"),
    BLACK("BLANK");

    private final String color;

    ColorEnum(final String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.color;
    }
}
