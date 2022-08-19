package ru.geekbrains.domain;

import java.util.Map;

public class HttpResponse {

    private int statusCode;
    private String statusCodeName;
    private String body;
    private Map<String, String> headers;

    public HttpResponse() {
    }

    public static HttpResponse create(int statusCode, String statusCodeName, String body, Map<String, String> headers) {
        HttpResponse response = new HttpResponse();
        response.setStatusCode(statusCode);
        response.setBody(body);
        response.setHeaders(headers);
        response.setStatusCodeName(statusCodeName);
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

    public String getStatusCodeName() {
        return statusCodeName;
    }

    public void setStatusCodeName(String statusCodeName) {
        this.statusCodeName = statusCodeName;
    }
}
