package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public static HttpRequest parse(Deque<String> rawRequest) {
        String[] firstLine = rawRequest.pop().split("\\s");
        Map<String, String> headers = new HashMap<>();
        while (!rawRequest.isEmpty()) {
            String header = rawRequest.pop();
            if (header.isEmpty())
                continue;
            String key = header.substring(0, header.indexOf(":"));
            String value = header.substring(header.indexOf(":") + 2);
            headers.put(key, value);
            if (key.equals("Content-Length"))
                break;
        }
        StringBuilder body = new StringBuilder();
        while (!rawRequest.isEmpty()) {
            body.append(rawRequest.pop());
        }
        return new HttpRequest(firstLine[0], firstLine[1], firstLine[2], headers, body.toString());
    }
}
