package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import static ru.geekbrains.handler.ResponseStatus.*;

@Handler(order = 1)
class PostMethodHandler extends MethodHandlerImpl {

    private static final String METHOD_NAME = "POST";

    public PostMethodHandler(MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) {
        super(METHOD_NAME, next, socketService, responseSerializer);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.createBuilder()
                .withStatusCode(OK.getCode())
                .withStatusCodeName(OK.getName())
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>POST method handled</h1>")
                .build();
    }
}
