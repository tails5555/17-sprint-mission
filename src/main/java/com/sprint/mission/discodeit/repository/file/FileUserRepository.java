package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.util.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class FileUserRepository implements UserRepository {
    // 사용자 객체를 저장할 디렉터리 경로
    private final Path path;

    public FileUserRepository() {
        this.path = Paths.get(
                System.getProperty("user.dir"),
                "data",
                "users"
        );

        FileUtils.init(path);
    }

    // 사용자 ID에 해당하는 파일 경로 반환
    private Path resolvePath(UUID userId) {
        return path.resolve(userId + ".ser");
    }

    @Override
    public User find(UUID userId) {
        Path filePath = resolvePath(userId);
        if (Files.notExists(filePath)) {
            throw new IllegalArgumentException(
                    "존재하지 않는 사용자입니다."
            );
        }

        return FileUtils.loadOne(filePath);
    }

    @Override
    public List<User> findAll() {
        return FileUtils.load(path);
    }

    @Override
    public User save(User user) {
        FileUtils.save(resolvePath(user.getId()), user);
        return user;
    }

    @Override
    public void delete(UUID userId) {
        FileUtils.delete(resolvePath(userId));
    }
}
