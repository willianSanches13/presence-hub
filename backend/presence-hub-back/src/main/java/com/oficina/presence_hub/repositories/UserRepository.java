package com.oficina.presence_hub.repositories;

import com.oficina.presence_hub.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
