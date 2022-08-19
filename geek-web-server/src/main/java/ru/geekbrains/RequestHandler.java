package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements Runnable {

    private final SocketService socketService;
    private final FileService fileService;
    private final RequestParser requestParser;
    private final ResponseSerializer responseSerializer;

    public RequestHandler(SocketService socketService,
                          FileService fileService,
                          RequestParser requestParser,
                          ResponseSerializer responseSerializer) {
        this.socketService = socketService;
        this.fileService = fileService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest request = requestParser.parse(rawRequest);

        if (!fileService.exists(request.getPath())) {
            response(404, "NOT_FOUND", "<h1>Файл не найден!</h1>");
            return;
        }
        if (fileService.isDirectory(request.getPath())) {
            response(500, "Internal Server Error", "<h1>Указанный путь является директорией!</h1>");
            return;
        }
        response(200, "OK",  fileService.readFile(request.getPath()));
    }

    private void response(int code, String codeName, String body) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=utf-8\n");
        String rawResponse = responseSerializer.serialize(HttpResponse.create(code, codeName, body, headers));
        socketService.writeResponse(rawResponse);
        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");
    }
}
