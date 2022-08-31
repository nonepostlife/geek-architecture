package ru.geekbrains.behavioral.state;

public class Default implements WritingState{
    @Override
    public void write(String words) {
        System.out.println(words);
    }
}
