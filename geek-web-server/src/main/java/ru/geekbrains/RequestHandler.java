package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestHandler implements Runnable {

    private final Socket socket;
    private final String folder;

    public RequestHandler(Socket socket, String folder) {
        this.socket = socket;
        this.folder = folder;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(socket.getOutputStream())
        ) {
            while (!input.ready()) ;

            RequestParser parser = new RequestParser(input);
            System.out.println(parser.getRequest());

            FileSystemService systemService = new FileSystemService(folder, parser.getPath());
            ResponseService responseService = new ResponseService(output, systemService);

            if (!systemService.isPathExist()) {
                responseService.response(ResponseCode.CODE_404, "File is not found!");
                System.out.println("Client disconnected!");
                return;
            }

            responseService.response(ResponseCode.CODE_200, "");
            System.out.println("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
