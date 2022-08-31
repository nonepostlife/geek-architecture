package ru.geekbrains.behavioral.mediator;

public class User {
    private String name;
    private ChatRoomMediator chat;

    public User(String name, ChatRoomMediator chat) {
        this.name = name;
        this.chat = chat;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        chat.showMessage(this, message);
    }
}
