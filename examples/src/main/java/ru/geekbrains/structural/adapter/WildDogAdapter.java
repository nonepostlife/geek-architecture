package ru.geekbrains.structural.adapter;

public class WildDogAdapter extends Lion {

    private final WildDog dog;

    public WildDogAdapter() {
        this.dog = new WildDog();
    }

    @Override
    public void roar() {
        dog.bark();
    }
}
