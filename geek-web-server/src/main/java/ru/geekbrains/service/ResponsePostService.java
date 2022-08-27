package ru.geekbrains.service;

import ru.geekbrains.domain.HttpRequest;

public class ResponsePostService implements ResponseService {

    private final ResponseService responseService;

    public ResponsePostService(ResponseService responseService) {
        this.responseService = responseService;
    }

    @Override
    public void response(HttpRequest request, int code, String codeName, String body) {
        System.out.println("Метод POST: создание данных");
        responseService.response(request, code, codeName, body);
    }
}
