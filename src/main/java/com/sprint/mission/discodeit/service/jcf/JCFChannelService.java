package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.*;

public class JCFChannelService implements ChannelService {
    // id로 바로 찾기 위해 Map을 씁니다. (key: id, value: Channel)
    private final Map<UUID, Channel> data;

    public JCFChannelService() {
        this.data = new HashMap<>();
    }

    @Override
    public Channel create(ChannelType type, String name, String description) {
        Channel channel = new Channel(type, name, description);
        data.put(channel.getId(), channel);
        return channel;
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
    public Channel update(UUID channelId, ChannelType type, String name, String description) {
        Channel channel = find(channelId);   // 없으면 여기서 예외가 발생합니다.
        channel.update(type, name, description);
        return channel;
    }

    @Override
    public void delete(UUID channelId) {
        if (!data.containsKey(channelId)) {
            throw new IllegalArgumentException("채널을 찾을 수 없습니다. id=" + channelId);
        }
        data.remove(channelId);
    }
}
