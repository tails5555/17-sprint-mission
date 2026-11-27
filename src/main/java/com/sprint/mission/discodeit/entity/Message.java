package com.sprint.mission.discodeit.entity;

import java.io.Serializable;
import java.util.UUID;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    // 공통 필드: 모든 도메인 모델이 가집니다.
    private final UUID id;
    private final Long createdAt;
    private Long updatedAt;

    // Message 만의 필드
    private String content;     // 내용
    private UUID channelId;     // 채널ID
    private UUID authorId;      // 사용자ID

    public Message(String content, UUID channelId, UUID authorId) {
        this.id = UUID.randomUUID();                  // 겹치지 않는 id를 만듭니다.
        this.createdAt = System.currentTimeMillis();  // 지금 시각(밀리초 단위 유닉스 타임스탬프)
        this.content = content;
        this.channelId = channelId;
        this.authorId = authorId;
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

    public String getContent() {
        return content;
    }

    public UUID getChannelId() {
        return channelId;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    // 값을 바꾸는 메서드. 바꿀 때마다 수정 시각을 기록합니다.
    // content 내용만 수정 가능하게 추가 개선
    public void update(String content) {
        this.content = content;
        this.updatedAt = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", content='" + content + '\'' +
                ", channelId=" + channelId +
                ", authorId=" + authorId +
                '}';
    }
}
