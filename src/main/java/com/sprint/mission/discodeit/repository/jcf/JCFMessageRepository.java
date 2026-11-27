package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.util.*;

public class JCFMessageRepository implements MessageRepository {
    // id로 바로 찾기 위해 Map을 씁니다. (key: id, value: Message)
    private final Map<UUID, Message> data;

    public JCFMessageRepository() {
        this.data = new HashMap<>();
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
    public Message save(Message message) {
        data.put(message.getId(), message);
        return message;
    }

    @Override
    public void delete(UUID messageId) {
        data.remove(messageId);
    }
}
