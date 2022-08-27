package ru.geekbrains.service;

import ru.geekbrains.domain.HttpRequest;

public class ResponseDeleteService implements ResponseService {

    private final ResponseService responseService;

    public ResponseDeleteService(ResponseService responseService) {
        this.responseService = responseService;
    }

    @Override
    public void response(HttpRequest request, int code, String codeName, String body) {
        System.out.println("Метод DELETE: удаление данных");
        responseService.response(request, code, codeName, body);
    }
}
