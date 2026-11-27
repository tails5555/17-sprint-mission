package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.util.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class FileChannelRepository implements ChannelRepository {
    // 채널 객체를 저장할 디렉터리 경로
    private final Path path;

    public FileChannelRepository() {
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
    public Channel save(Channel channel) {
        FileUtils.save(resolvePath(channel.getId()), channel);
        return channel;
    }

    @Override
    public void delete(UUID channelId) {
        FileUtils.delete(resolvePath(channelId));
    }
}
