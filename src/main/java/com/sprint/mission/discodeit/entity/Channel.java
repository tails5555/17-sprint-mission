package com.sprint.mission.discodeit.entity;

import java.io.Serializable;
import java.util.UUID;

public class Channel implements Serializable {
    private static final long serialVersionUID = 1L;

    // 공통 필드: 모든 도메인 모델이 가집니다.
    private final UUID id;
    private final Long createdAt;
    private Long updatedAt;

    // Channel 만의 필드
    private ChannelType type;       // 채널 종류
    private String name;            // 이름
    private String description;     // 설명

    public Channel(ChannelType type, String name, String description) {
        this.id = UUID.randomUUID();                  // 겹치지 않는 id를 만듭니다.
        this.createdAt = System.currentTimeMillis();  // 지금 시각(밀리초 단위 유닉스 타임스탬프)
        this.type = type;
        this.name = name;
        this.description = description;
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

    public ChannelType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // 값을 바꾸는 메서드. 바꿀 때마다 수정 시각을 기록합니다.
    public void update(ChannelType type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.updatedAt = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
