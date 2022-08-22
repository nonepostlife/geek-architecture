package ru.geekbrains.service;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseGetService implements ResponseService {
    private final ResponseSerializer responseSerializer;
    private final SocketService socketService;

    public ResponseGetService(ResponseSerializer responseSerializer, SocketService socketService) {
        this.responseSerializer = responseSerializer;
        this.socketService = socketService;
    }

    @Override
    public void response(HttpRequest request, int code, String codeName, String body) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=utf-8\n");
        HttpResponse httpResponse = HttpResponse.createBuilder()
                .withStatusCode(code)
                .withStatusCodeName(codeName)
                .withBody(body)
                .withHeaders(headers)
                .build();
        String rawResponse = responseSerializer.serialize(httpResponse);
        socketService.writeResponse(rawResponse);
        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");
    }
}
