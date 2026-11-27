package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository {
    Message find(UUID messageId);                                     // 읽기(단건)
    List<Message> findAll();                                          // 모두 읽기
    Message save(Message message);                                    // 저장 (등록, 수정)
    void delete(UUID messageId);                                      // 삭제
}
