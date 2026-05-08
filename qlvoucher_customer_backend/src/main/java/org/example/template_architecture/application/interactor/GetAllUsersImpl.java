package org.example.template_architecture.application.interactor;

import org.example.template_architecture.application.dto.response.UserResponse;
import org.example.template_architecture.application.input.IGetAllUsers;
import org.example.template_architecture.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllUsersImpl implements IGetAllUsers {

    private final UserRepository userRepository;

    public GetAllUsersImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> execute() {
        // Lấy danh sách User từ DB và map sang list UserResponse DTO
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getPhone()
                ))
                .collect(Collectors.toList());
    }
}
