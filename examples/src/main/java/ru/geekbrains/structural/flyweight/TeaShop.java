package ru.geekbrains.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TeaShop {

    private final TeaMaker teaMaker;
    private final Map<Integer, Tea> orders;

    public TeaShop(TeaMaker teaMaker) {
        this.teaMaker = teaMaker;
        orders = new HashMap<>();
    }

    public void takeOrder(String preference, int table) {
        orders.put(table, teaMaker.makeTea(preference));
    }

    public void serve() {
        orders.forEach((k, v) -> System.out.println("Подаем чай на стол " + k));
    }
}
