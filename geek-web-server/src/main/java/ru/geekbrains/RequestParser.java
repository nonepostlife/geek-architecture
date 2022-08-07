package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestParser {
    private String type;
    private String path;
    private String request;

    public RequestParser(BufferedReader input) {
        try {
            this.type = input.readLine();
            this.path = type.split(" ")[1];
            StringBuilder sb = new StringBuilder();
            sb.append(type).append("\n");
            while (input.ready()) {
                sb.append(input.readLine()).append("\n");
            }
            this.request = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public String getRequest() {
        return request;
    }
}
