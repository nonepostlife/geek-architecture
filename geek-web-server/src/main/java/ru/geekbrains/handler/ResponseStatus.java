package ru.geekbrains.handler;

public enum ResponseStatus {

    OK(200, "OK"),
    NOT_FOUND(404, "NOT_FOUND"),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED");

    private final int code;
    private final String name;

    ResponseStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
