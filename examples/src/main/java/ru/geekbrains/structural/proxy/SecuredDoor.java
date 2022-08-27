package ru.geekbrains.structural.proxy;

public class SecuredDoor {

    private Door door;

    public SecuredDoor(Door door) {
        this.door = door;
    }

    public void open(String password) {
        if (authenticate(password))
            this.door.open();
        else {
            System.out.println("Неверный пароль! Дверь не открывается");
        }
    }

    public boolean authenticate(String password) {
        return "1234qwerty".equals(password);
    }

    public void close() {
        this.door.close();
    }
}
