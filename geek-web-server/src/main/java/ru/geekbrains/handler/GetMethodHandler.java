package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import static ru.geekbrains.handler.ResponseStatus.*;

@Handler(order = 0)
class GetMethodHandler extends MethodHandlerImpl {

    private final FileService fileService;
    private static final String METHOD_NAME = "GET";

    public GetMethodHandler(MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) {
        super(METHOD_NAME, next, socketService, responseSerializer);
        this.fileService = fileService;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        if (!fileService.exists(request.getUrl())) {
            return HttpResponse.createBuilder()
                    .withStatusCode(NOT_FOUND.getCode())
                    .withStatusCodeName(NOT_FOUND.getName())
                    .withHeader("Content-Type", "text/html; charset=utf-8")
                    .build();
        }

        return HttpResponse.createBuilder()
                .withStatusCode(OK.getCode())
                .withStatusCodeName(OK.getName())
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody(fileService.readFile(request.getUrl()))
                .build();
    }
}
