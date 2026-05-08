package org.example.template_architecture.application.mapper;

import org.example.template_architecture.domain.entity.User;
import org.example.template_architecture.infrastructure.persistence.UserDbEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Chuyển từ Domain Model sang Database Entity (để lưu xuống DB)
    public UserDbEntity toDbEntity(User domain) {
        if (domain == null) return null;

        UserDbEntity entity = new UserDbEntity();
        entity.setId(domain.getId());
        entity.setFullName(domain.getFullName());
        entity.setEmail(domain.getEmail());
        entity.setPhone(domain.getPhone());
        return entity;
    }

    // Chuyển từ Database Entity lên Domain Model (để trả về cho Application Layer)
    public User toDomain(UserDbEntity entity) {
        if (entity == null) return null;

        return new User(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPhone()
        );
    }
}
