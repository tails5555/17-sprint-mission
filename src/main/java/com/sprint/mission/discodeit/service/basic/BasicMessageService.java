package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.List;
import java.util.UUID;

public class BasicMessageService implements MessageService {
    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    public BasicMessageService(
        MessageRepository messageRepository
        , ChannelRepository channelRepository
        , UserRepository userRepository
    ) {
        this.messageRepository = messageRepository;
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Message create(String content, UUID channelId, UUID authorId) {
        // 채널 및 사용자 유효성 검증
        channelRepository.find(channelId);
        userRepository.find(authorId);
        Message message = new Message(content, channelId, authorId);
        return messageRepository.save(message);
    }

    @Override
    public Message find(UUID messageId) {
        return messageRepository.find(messageId);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message update(UUID messageId, String content, UUID channelId, UUID authorId) {
        Message message = find(messageId); // 없으면 여기서 예외가 발생합니다.

        // 채널 및 사용자 유효성 검증
        channelRepository.find(channelId);
        userRepository.find(authorId);
        return messageRepository.save(message);
    }

    @Override
    public void delete(UUID messageId) {
        find(messageId); // 없으면 여기서 예외가 발생합니다.
        messageRepository.delete(messageId);
    }
}
