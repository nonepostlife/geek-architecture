package ru.geekbrains.structural.decorator;

public class SimpleCoffee extends Coffee{
    @Override
    public String getDescription() {
        return "Обычный кофе";
    }

    @Override
    public int getCost() {
        return 10;
    }
}
