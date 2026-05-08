package org.example.template_architecture.domain.repository;

import org.example.template_architecture.domain.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();

    // Hàm quan trọng để Use Case gọi lúc tạo User
    boolean existsByEmail(String email);
}
