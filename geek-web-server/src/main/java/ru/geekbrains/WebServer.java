package ru.geekbrains;

import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.service.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static Builder createBuilder() {
        return new Builder();
    }

    static class Builder {

        private ServerConfig serverConfig;
        private RequestParser requestParser;
        private ResponseSerializer responseSerializer;

        public Builder() {
        }

        public Builder withServerConfig(ServerConfig serverConfig) {
            this.serverConfig = serverConfig;
            return this;
        }

        public Builder withRequestParser(RequestParser requestParser) {
            this.requestParser = requestParser;
            return this;
        }

        public Builder withResponseSerializer(ResponseSerializer responseSerializer) {
            this.responseSerializer = responseSerializer;
            return this;
        }

        public void build() {
            try (ServerSocket serverSocket = new ServerSocket(serverConfig.getPort())) {
                System.out.println("Server started!");

                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("New client connected!");

                    new Thread(new RequestHandler(
                            SocketServiceFactory.create(socket),
                            FileServiceFactory.create(serverConfig.getWww()),
                            requestParser,
                            responseSerializer
                    )).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
