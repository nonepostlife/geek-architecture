package ru.geekbrains;

import ru.geekbrains.domain.HttpResponse;

public class ResponseSerializer {

    public static String serialize(HttpResponse response) {
        StringBuilder answer = new StringBuilder();
        answer.append("HTTP/1.1 ").append(response.getStatusCode());
        switch (response.getStatusCode()) {
            case 200:
                answer.append(" OK\n");
                break;
            case 404:
                answer.append(" NOT_FOUND\n");
                break;
            case 500:
                answer.append(" Internal Server Error\n");
                break;
        }
        response.getHeaders().forEach((k, v) -> answer.append(k).append(": ").append(v).append("\n"));
        answer.append("\n").append(response.getBody());
        return answer.toString();
    }
}
