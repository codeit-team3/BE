package com.codeit.sprint.team3.backend.auth.application.port.out.user;

import com.codeit.sprint.team3.backend.auth.domain.model.User;

public interface UpdateUserPort {
    void update(User user);
}
