package ru.geekbrains.structural.adapter;

public class Hunter {
    public void hunt (Lion lion) {
        lion.roar();
        System.out.println("Охотник поймал зверя!");
    }
}
