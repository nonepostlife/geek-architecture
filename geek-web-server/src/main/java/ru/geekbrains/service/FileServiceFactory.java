package ru.geekbrains.service;

public class FileServiceFactory {

    public static FileService create(String url) {
        return new FileServiceImpl(url);
    }
}
