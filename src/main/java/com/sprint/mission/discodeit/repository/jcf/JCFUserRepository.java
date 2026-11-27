package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.util.*;

public class JCFUserRepository implements UserRepository {
    // id로 바로 찾기 위해 Map을 씁니다. (key: id, value: User)
    private final Map<UUID, User> data;

    public JCFUserRepository() {
        this.data = new HashMap<>();
    }

    @Override
    public User find(UUID userId) {
        User user = data.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다. id=" + userId);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public User save(User user) {
        this.data.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(UUID userId) {
        data.remove(userId);
    }
}
