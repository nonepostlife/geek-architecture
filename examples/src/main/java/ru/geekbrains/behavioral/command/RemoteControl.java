package ru.geekbrains.behavioral.command;

public class RemoteControl {

    public void submit(Command command) {
        command.execute();
    }
}
