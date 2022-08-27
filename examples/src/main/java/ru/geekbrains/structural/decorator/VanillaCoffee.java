package ru.geekbrains.structural.decorator;

public class VanillaCoffee extends Coffee {
    private final Coffee coffee;

    public VanillaCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", ваниль";
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 3;
    }
}
