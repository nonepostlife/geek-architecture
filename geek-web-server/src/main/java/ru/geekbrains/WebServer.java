package ru.geekbrains;

import ru.geekbrains.properties.ServerProperties;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {
        ServerProperties serverProperties = new ServerProperties();

        try (ServerSocket serverSocket = new ServerSocket(serverProperties.getPort())) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                new Thread(new RequestHandler(new SocketService(socket), new FileService(serverProperties.getDirectory()))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
