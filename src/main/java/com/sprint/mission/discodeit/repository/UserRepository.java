package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User find(UUID userId);                                        // 읽기(단건)
    List<User> findAll();                                          // 모두 읽기
    User save(User user);                                          // 저장 (등록, 수정)
    void delete(UUID userId);                                      // 삭제
}
