package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;
import java.util.UUID;

public class BasicUserService implements UserService {
    private UserRepository userRepository;

    public BasicUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String username, String email, String password) {
        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    @Override
    public User find(UUID channelId) {
        return userRepository.find(channelId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(UUID userId, String username, String email, String password) {
        User user = userRepository.find(userId); // 없으면 여기서 예외가 발생합니다.
        user.update(username, email, password);
        return userRepository.save(user);
    }

    @Override
    public void delete(UUID channelId) {
        find(channelId); // 없으면 여기서 예외가 발생합니다.
        userRepository.delete(channelId);
    }
}
