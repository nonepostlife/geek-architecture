package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    public HttpRequest parse(Deque<String> rawRequest) {
        String[] firstLine = rawRequest.pop().split("\\s");
        Map<String, String> headers = new HashMap<>();
        while (!rawRequest.isEmpty()) {
            String line = rawRequest.pop();
            if (line.isEmpty())
                continue;
            String[] header = line.split(": ");
            headers.put(header[0], header[1]);
            if (header[0].equals("Content-Length"))
                break;
        }
        StringBuilder body = new StringBuilder();
        while (!rawRequest.isEmpty()) {
            body.append(rawRequest.pop());
        }
        return HttpRequest.createBuilder()
                .withMethod(firstLine[0])
                .withPath(firstLine[1])
                .withHttpVersion(firstLine[2])
                .withHeaders(headers)
                .withBody(body.toString())
                .build();
    }
}
