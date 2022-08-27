package ru.geekbrains.structural.decorator;

public class MilkCoffee extends Coffee {
    private final Coffee coffee;

    public MilkCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", молоко";
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 2;
    }
}
