package ru.geekbrains.iterator;

import java.util.Iterator;
import java.util.Random;

public class RandomNum implements Iterable<Integer> {
    private Random random = new Random();
    private int count;
    private final int min;
    private final int max;

    public RandomNum(int count, int min, int max) {
        this.count = count;
        this.min = min;
        this.max = max;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Integer next() {
                count--;
                return random.nextInt(max - min) + min;
            }
        };
    }
}
