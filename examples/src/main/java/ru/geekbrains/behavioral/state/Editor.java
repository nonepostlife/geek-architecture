package ru.geekbrains.behavioral.state;

public class Editor {
    private WritingState state;

    public Editor(WritingState state) {
        this.state = state;
    }

    public void setState(WritingState state) {
        this.state = state;
    }

    public void type(String words) {
        state.write(words);
    }
}
