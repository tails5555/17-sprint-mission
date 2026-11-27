package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.util.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class FileMessageRepository implements MessageRepository {
    // 메시지 객체를 저장할 디렉터리 경로
    private final Path path;

    public FileMessageRepository() {
        this.path = Paths.get(
                System.getProperty("user.dir"),
                "data",
                "messages"
        );
        FileUtils.init(path);
    }

    // 메시지 ID에 해당하는 파일 경로 반환
    private Path resolvePath(UUID messageId) {
        return path.resolve(messageId + ".ser");
    }

    @Override
    public Message find(UUID messageId) {
        Path filePath = resolvePath(messageId);
        if (Files.notExists(filePath)) {
            throw new IllegalArgumentException(
                    "존재하지 않는 메시지입니다."
            );
        }
        return FileUtils.loadOne(filePath);
    }

    @Override
    public List<Message> findAll() {
        return FileUtils.load(path);
    }

    @Override
    public Message save(Message message) {
        FileUtils.save(resolvePath(message.getId()), message);
        return message;
    }

    @Override
    public void delete(UUID messageId) {
        FileUtils.delete(resolvePath(messageId));
    }
}
