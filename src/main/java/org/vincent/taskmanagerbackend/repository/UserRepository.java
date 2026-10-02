package org.vincent.taskmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vincent.taskmanagerbackend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
