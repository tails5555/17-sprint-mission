package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.UUID;

public interface ChannelRepository {
    Channel find(UUID channelId);                                        // 읽기(단건)
    List<Channel> findAll();                                             // 모두 읽기
    Channel save(Channel channel);                                       // 저장 (등록, 수정)
    void delete(UUID channelId);                                         // 삭제
}
