package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.handler.MethodHandler;
import ru.geekbrains.handler.MethodHandlerFactory;
import ru.geekbrains.service.*;

import java.io.IOException;
import java.util.Deque;

public class RequestHandler implements Runnable {

    private final SocketService socketService;
    private final RequestParser requestParser;
    private final MethodHandler methodHandler;

    public RequestHandler(SocketService socketService,
                          RequestParser requestParser,
                          MethodHandler methodHandler
    ) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.methodHandler = methodHandler;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest request = requestParser.parse(rawRequest);

        methodHandler.handle(request);

        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");
    }
}
