package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    Channel create(ChannelType type, String name, String description);   // 생성
    Channel find(UUID channelId);                                        // 읽기(단건)
    List<Channel> findAll();                                             // 모두 읽기
    Channel update(UUID channelId, ChannelType type, String name, String description); // 수정
    void delete(UUID channelId);
}
