package com.sprint.mission.discodeit.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {
    // 디렉터리 초기화
    public static void init(Path directory) {
        try {
            Files.createDirectories(directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 객체 저장 및 수정
    public static <T extends Serializable> void save(Path filePath, T data) {
        try (
                FileOutputStream fos = new FileOutputStream(filePath.toFile());
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 단일 객체 조회
    @SuppressWarnings("unchecked")
    public static <T> T loadOne(Path filePath) {
        try (
                FileInputStream fis = new FileInputStream(filePath.toFile());
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 전체 객체 조회
    public static <T> List<T> load(Path directory) {
        if (Files.notExists(directory)) {
            return List.of();
        }

        try (Stream<Path> paths = Files.list(directory)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".ser"))
                    .map(path -> FileUtils.<T>loadOne(path))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 객체 파일 삭제
    public static void delete(Path filePath) {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
