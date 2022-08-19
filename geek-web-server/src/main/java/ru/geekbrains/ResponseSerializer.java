package ru.geekbrains;

import ru.geekbrains.domain.HttpResponse;

public class ResponseSerializer {

    public String serialize(HttpResponse response) {
        StringBuilder answer = new StringBuilder();
        answer.append("HTTP/1.1 ").append(response.getStatusCode()).append(" ").append(response.getStatusCodeName()).append("\n");
        response.getHeaders().forEach((k, v) -> answer.append(k).append(": ").append(v).append("\n"));
        answer.append("\n").append(response.getBody());
        return answer.toString();
    }
}
