package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.util.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class FileChannelService implements ChannelService {
    // 채널 객체를 저장할 디렉터리 경로
    private final Path path;

    public FileChannelService() {
        this.path = Paths.get(
                System.getProperty("user.dir"),
                "data",
                "channels"
        );

        FileUtils.init(path);
    }

    // 채널 ID에 해당하는 파일 경로 반환
    private Path resolvePath(UUID channelId) {
        return path.resolve(channelId + ".ser");
    }

    @Override
    public Channel create(ChannelType type, String name, String description) {
        Channel channel = new Channel(type, name, description);
        FileUtils.save(resolvePath(channel.getId()), channel);
        return channel;
    }

    @Override
    public Channel find(UUID channelId) {
        Path filePath = resolvePath(channelId);
        if (Files.notExists(filePath)) {
            throw new IllegalArgumentException(
                    "존재하지 않는 채널입니다."
            );
        }
        return FileUtils.loadOne(filePath);
    }

    @Override
    public List<Channel> findAll() {
        return FileUtils.load(path);
    }

    @Override
    public Channel update(UUID channelId, ChannelType type, String name, String description) {
        Channel channel = find(channelId);
        channel.update(type, name, description);
        FileUtils.save(resolvePath(channelId), channel);
        return channel;
    }

    @Override
    public void delete(UUID channelId) {
        // 채널 존재 여부 검증
        find(channelId);
        FileUtils.delete(resolvePath(channelId));
    }
}
