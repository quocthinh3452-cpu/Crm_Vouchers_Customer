package org.example.template_architecture.application.input;

import org.example.template_architecture.application.dto.request.UserRequest;
import org.example.template_architecture.application.dto.response.UserResponse;

public interface ICreateUser {
    UserResponse execute(UserRequest request);
}
