package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.util.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class FileMessageService implements MessageService {
    // 메시지 객체를 저장할 디렉터리 경로
    private final Path path;
    private final ChannelService channelService;
    private final UserService userService;

    public FileMessageService(ChannelService channelService, UserService userService) {
        this.path = Paths.get(
                System.getProperty("user.dir"),
                "data",
                "messages"
        );
        FileUtils.init(path);

        this.channelService = channelService;
        this.userService = userService;
    }

    // 메시지 ID에 해당하는 파일 경로 반환
    private Path resolvePath(UUID messageId) {
        return path.resolve(messageId + ".ser");
    }

    @Override
    public Message create(String content, UUID channelId, UUID authorId) {
        // 채널 및 사용자 유효성 검증
        channelService.find(channelId);
        userService.find(authorId);

        Message message = new Message(content, channelId, authorId);
        FileUtils.save(resolvePath(message.getId()), message);
        return message;
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
    public Message update(UUID messageId, String content, UUID channelId, UUID authorId) {
        // 채널 및 사용자 유효성 검증
        channelService.find(channelId);
        userService.find(authorId);

        Message message = find(messageId);
        message.update(content, channelId, authorId);
        FileUtils.save(resolvePath(messageId), message);
        return message;
    }

    @Override
    public void delete(UUID messageId) {
        // 메시지 존재 여부 검증
        find(messageId);
        FileUtils.delete(resolvePath(messageId));
    }
}
