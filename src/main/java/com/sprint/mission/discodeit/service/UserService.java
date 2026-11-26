package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User create(String username, String email, String password);   // 생성
    User find(UUID userId);                                        // 읽기(단건)
    List<User> findAll();                                          // 모두 읽기
    User update(UUID userId, String username, String email, String password); // 수정
    void delete(UUID userId);                                      // 삭제
}