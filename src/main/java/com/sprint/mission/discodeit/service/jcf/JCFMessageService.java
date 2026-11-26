package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.*;

public class JCFMessageService implements MessageService {
    // id로 바로 찾기 위해 Map을 씁니다. (key: id, value: Message)
    private final Map<UUID, Message> data;

    public JCFMessageService() {
        this.data = new HashMap<>();
    }

    @Override
    public Message create(String content, UUID channelId, UUID authorId) {
        Message message = new Message(content, channelId, authorId);
        data.put(message.getId(), message);
        return message;
    }

    @Override
    public Message find(UUID messageId) {
        Message message = data.get(messageId);
        if (message == null) {
            throw new IllegalArgumentException("메시지를 찾을 수 없습니다. id=" + messageId);
        }
        return message;
    }

    @Override
    public List<Message> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Message update(UUID messageId, String content, UUID channelId, UUID authorId) {
        Message message = find(messageId);   // 없으면 여기서 예외가 발생합니다.
        message.update(content, channelId, authorId);
        return message;
    }

    @Override
    public void delete(UUID messageId) {
        if (!data.containsKey(messageId)) {
            throw new IllegalArgumentException("메시지를 찾을 수 없습니다. id=" + messageId);
        }
        data.remove(messageId);
    }
}
