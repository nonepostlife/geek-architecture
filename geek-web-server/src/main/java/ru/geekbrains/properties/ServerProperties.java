package ru.geekbrains.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerProperties {
    private int port;
    private String directory;

    public ServerProperties() {
        try (InputStream input = new FileInputStream("geek-web-server/src/main/resources/application.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            port = Integer.parseInt(prop.getProperty("server.port"));
            directory = prop.getProperty("server.directory");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public String getDirectory() {
        return directory;
    }
}