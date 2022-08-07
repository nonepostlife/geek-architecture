package ru.geekbrains;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class ResponseService {

    private final PrintWriter output;
    private final FileSystemService systemService;

    public ResponseService(PrintWriter output, FileSystemService systemService) {
        this.output = output;
        this.systemService = systemService;
    }

    public void response(ResponseCode code, String message) {
        switch (code) {
            case CODE_200: {
                output.println("HTTP/1.1 200 OK");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                try {
                    Files.newBufferedReader(systemService.getRoot()).transferTo(output);
                } catch (IOException e) {
                    response(ResponseCode.CODE_500, e.getMessage());
                }
                break;
            }
            case CODE_404: {
                output.println("HTTP/1.1 404 NOT_FOUND");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                output.println("<h1>" + message + "</h1>");
                output.flush();
                break;
            }
            case CODE_500: {
                output.println("HTTP/1.1 500 ERROR");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                output.println("<h1>" + message + "</h1>");
                output.flush();
                break;
            }
        }
    }
}
