package ru.geekbrains.service;

import ru.geekbrains.domain.HttpRequest;

public class ResponsePutService implements ResponseService {

    private final ResponseService responseService;

    public ResponsePutService(ResponseService responseService) {
        this.responseService = responseService;
    }

    @Override
    public void response(HttpRequest request, int code, String codeName, String body) {
        System.out.println("Метод PUT: обновление данных");
        responseService.response(request, code, codeName, body);
    }
}
