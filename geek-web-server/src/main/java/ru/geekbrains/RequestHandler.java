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

    public RequestHandler(SocketService socketService, FileService fileService) {
        this.socketService = socketService;
        this.fileService = fileService;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest request = RequestParser.parse(rawRequest);

        if (!fileService.exists(request.getPath())) {
            response(404, "<h1>Файл не найден!</h1>");
            return;
        }
        if (fileService.isDirectory(request.getPath())) {
            response(500, "<h1>Указанный путь является директорией!</h1>");
            return;
        }
        response(200, fileService.readFile(request.getPath()));
    }

    private void response(int code, String body) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=utf-8\n");
        String rawResponse = ResponseSerializer.serialize(HttpResponse.create(code, body, headers));
        socketService.writeResponse(rawResponse);
        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");
    }
}
