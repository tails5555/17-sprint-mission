package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.util.*;

public class JCFChannelRepository implements ChannelRepository {
    // id로 바로 찾기 위해 Map을 씁니다. (key: id, value: Channel)
    private final Map<UUID, Channel> data;

    public JCFChannelRepository() {
        this.data = new HashMap<>();
    }

    @Override
    public Channel find(UUID channelId) {
        Channel channel = data.get(channelId);
        if (channel == null) {
            throw new IllegalArgumentException("채널을 찾을 수 없습니다. id=" + channelId);
        }
        return channel;
    }

    @Override
    public List<Channel> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Channel save(Channel channel) {
        data.put(channel.getId(), channel);
        return channel;
    }

    @Override
    public void delete(UUID channelId) {
        data.remove(channelId);
    }
}
