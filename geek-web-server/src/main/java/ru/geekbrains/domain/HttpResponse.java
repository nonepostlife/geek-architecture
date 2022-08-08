package ru.geekbrains.domain;

import java.util.Map;

public class HttpResponse {

    private int statusCode;
    private String body;
    private Map<String, String> headers;

    public static HttpResponse create(int statusCode, String body, Map<String, String> headers) {
        HttpResponse response = new HttpResponse();
        response.setStatusCode(statusCode);
        response.setBody(body);
        response.setHeaders(headers);
        return response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
