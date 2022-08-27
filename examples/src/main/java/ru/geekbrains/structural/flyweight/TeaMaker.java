package ru.geekbrains.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TeaMaker {

    private final Map<String, Tea> teaList;

    public TeaMaker() {
        teaList = new HashMap<>();
    }

    public Tea makeTea(String preference) {
        if (!teaList.containsKey(preference)) {
            teaList.put(preference, new Tea());
        }
        return teaList.get(preference);
    }
}
