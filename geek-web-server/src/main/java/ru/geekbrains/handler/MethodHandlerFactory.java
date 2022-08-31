package ru.geekbrains.handler;

import org.reflections.Reflections;
import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public final class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, ServerConfig config, FileService fileService) {
        PutMethodHandler putHandler = new PutMethodHandler(null, socketService, responseSerializer, config, fileService);
        PostMethodHandler postHandler = new PostMethodHandler(putHandler, socketService, responseSerializer, config, fileService);
        return new GetMethodHandler(postHandler, socketService, responseSerializer, config, fileService);
    }

    public static MethodHandler createAnnotated(SocketService socketService, ResponseSerializer responseSerializer, ServerConfig config, FileService fileService) {
        Reflections reflections = new Reflections("ru.geekbrains.handler");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Handler.class);
        Set<Class<?>> collect = classes.stream()
                .sorted((o1, o2) -> Integer.compare(o2.getAnnotation(Handler.class).order(), o1.getAnnotation(Handler.class).order()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        MethodHandlerImpl prev = null, next = null;
        try {
            if (collect.iterator().hasNext()) {
                next = (MethodHandlerImpl) collect.iterator().next().getConstructor(MethodHandlerImpl.class, SocketService.class, ResponseSerializer.class, ServerConfig.class, FileService.class).newInstance(null, socketService, responseSerializer, config, fileService);
                collect.remove(collect.iterator().next());
                for (Class<?> c : collect) {
                    prev = (MethodHandlerImpl) c.getConstructor(MethodHandlerImpl.class, SocketService.class, ResponseSerializer.class, ServerConfig.class, FileService.class).newInstance(next, socketService, responseSerializer, config, fileService);
                    next = prev;
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (prev == null) {
            if (next != null)
                return next;
            throw new IllegalStateException("Not found class with annotation Handler");
        }
        return prev;
    }
}
