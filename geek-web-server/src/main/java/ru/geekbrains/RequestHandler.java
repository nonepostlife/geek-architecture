package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.*;

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

        ResponseService responseService = new ResponseGetService(responseSerializer, socketService);
        switch (request.getMethod()) {
            case "POST":
                responseService = new ResponsePostService(responseService);
                break;
            case "PUT":
                responseService = new ResponsePutService(responseService);
                break;
            case "DELETE":
                responseService = new ResponseDeleteService(responseService);
                break;
            default:
                break;
        }
        
        if (!fileService.exists(request.getPath())) {
            responseService.response(request, 404, "NOT_FOUND", "<h1>Файл не найден!</h1>");
            return;
        }
        if (fileService.isDirectory(request.getPath())) {
            responseService.response(request, 500, "Internal Server Error", "<h1>Указанный путь является директорией!</h1>");
            return;
        }
        responseService.response(request, 200, "OK", fileService.readFile(request.getPath()));
    }
}
