package ru.geekbrains;

import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.config.ServerConfigFactory;
import ru.geekbrains.service.ResponseGetService;

public class Main {
    public static void main(String[] args) {
        ServerConfig serverConfig = ServerConfigFactory.create(args);

        WebServer.createBuilder()
                .withServerConfig(serverConfig)
                .withRequestParser(new RequestParser())
                .withResponseSerializer(new ResponseSerializer())
                .build();
    }
}
