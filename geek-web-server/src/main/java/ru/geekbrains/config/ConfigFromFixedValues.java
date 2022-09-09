package ru.geekbrains.config;

class ConfigFromFixedValues implements ServerConfig {

    private static final String DEFAULT_URL = "www";
    private static final int DEFAULT_PORT = 8088;

    @Override
    public String getWww() {
        return DEFAULT_URL;
    }

    @Override
    public int getPort() {
        return DEFAULT_PORT;
    }
}
