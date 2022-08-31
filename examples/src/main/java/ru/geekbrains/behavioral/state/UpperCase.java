package ru.geekbrains.behavioral.state;

import java.util.Locale;

public class UpperCase implements WritingState {
    @Override
    public void write(String words) {
        System.out.println(words.toUpperCase());
    }
}
