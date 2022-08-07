package ru.geekbrains;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {

    private Path root;

    public FileSystemService(String folder, String path) {
        this.root = Paths.get(folder, path);
    }

    public Path getRoot() {
        return root;
    }

    public boolean isPathExist() {
        return Files.exists(root);
    }

    public boolean isDirectory() {
        return root.toFile().isDirectory();
    }
}
