package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class User {
    // 공통 필드: 모든 도메인 모델이 가집니다.
    private final UUID id;
    private final Long createdAt;
    private Long updatedAt;

    // User 만의 필드
    private String username;    // 사용자 이름
    private String email;       // 이메일
    private String password;    // 비밀번호

    public User(String username, String email, String password) {
        this.id = UUID.randomUUID();                  // 겹치지 않는 id를 만듭니다.
        this.createdAt = System.currentTimeMillis();  // 지금 시각(밀리초 단위 유닉스 타임스탬프)
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // 값을 바꾸는 메서드. 바꿀 때마다 수정 시각을 기록합니다.
    public void update(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.updatedAt = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
