package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.*;

public class JCFUserService implements UserService {
    // id로 바로 찾기 위해 Map을 씁니다. (key: id, value: User)
    private final Map<UUID, User> data;

    public JCFUserService() {
        this.data = new HashMap<>();
    }

    @Override
    public User create(String username, String email, String password) {
        User user = new User(username, email, password);
        data.put(user.getId(), user);
        return user;
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
    public User update(UUID userId, String username, String email, String password) {
        User user = find(userId);   // 없으면 여기서 예외가 발생합니다.
        user.update(username, email, password);
        return user;
    }

    @Override
    public void delete(UUID userId) {
        if (!data.containsKey(userId)) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다. id=" + userId);
        }
        data.remove(userId);
    }
}
