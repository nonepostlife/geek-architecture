package ru.geekbrains.structural.proxy;

public class LabDoor extends Door {
    @Override
    public void open() {
        System.out.println("Открытие двери в лабораторию");
    }

    @Override
    public void close() {
        System.out.println("Закрытие двери в лабораторию");
    }
}
