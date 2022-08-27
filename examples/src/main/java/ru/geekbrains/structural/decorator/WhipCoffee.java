package ru.geekbrains.structural.decorator;

public class WhipCoffee extends Coffee {
    private final Coffee coffee;

    public WhipCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", сливки";
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 5;
    }
}
