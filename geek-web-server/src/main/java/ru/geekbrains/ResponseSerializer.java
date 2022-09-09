package ru.geekbrains;

import ru.geekbrains.domain.HttpResponse;

public class ResponseSerializer {

    private static final String HTTP_VERSION = "HTTP/1.1";

    public String serialize(HttpResponse response) {
        StringBuilder answer = new StringBuilder();
        answer.append(HTTP_VERSION).append(" ").append(response.getStatusCode()).append(" ").append(response.getStatusCodeName()).append("\n");
        response.getHeaders().forEach((k, v) -> answer.append(k).append(": ").append(v).append("\n"));
        answer.append("\n").append(response.getBody());
        return answer.toString();
    }
}
