package org.example.template_architecture.application.interactor;

import org.example.template_architecture.application.dto.request.UserRequest;
import org.example.template_architecture.application.dto.response.UserResponse;
import org.example.template_architecture.application.input.ICreateUser; // Nhớ tạo interface này
import org.example.template_architecture.domain.entity.User;
import org.example.template_architecture.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserImpl implements ICreateUser {

    private final UserRepository userRepository;

    public CreateUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserResponse execute(UserRequest request) {
        // 1. Validate Email không được trùng
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email này đã được sử dụng: " + request.getEmail());
        }

        // 2. Map sang Domain
        User newUser = new User(
                null,
                request.getFullName(),
                request.getEmail(),
                request.getPhone()
        );

        // 3. Lưu xuống DB
        User savedUser = userRepository.save(newUser);

        // 4. Trả về Response
        return new UserResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getPhone()
        );
    }
}