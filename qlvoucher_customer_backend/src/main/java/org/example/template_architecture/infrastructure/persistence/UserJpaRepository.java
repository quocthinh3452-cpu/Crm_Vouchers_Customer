package org.example.template_architecture.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDbEntity, Long> {
    // Hàm này cực kỳ quan trọng để check trùng Email ở Use Case
    boolean existsByEmail(String email);
}
