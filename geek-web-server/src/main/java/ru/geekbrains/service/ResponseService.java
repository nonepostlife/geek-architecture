package ru.geekbrains.service;

import ru.geekbrains.domain.HttpRequest;

public interface ResponseService {

    void response(HttpRequest request, int code, String codeName, String body);
}
