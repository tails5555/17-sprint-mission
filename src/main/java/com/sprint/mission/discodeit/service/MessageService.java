package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    Message create(String content, UUID channelId, UUID authorId);    // 생성
    Message find(UUID messageId);                                     // 읽기(단건)
    List<Message> findAll();                                          // 모두 읽기
    Message update(UUID messageId, String content, UUID channelId, UUID authorId);  // 수정
    void delete(UUID messageId);
}
