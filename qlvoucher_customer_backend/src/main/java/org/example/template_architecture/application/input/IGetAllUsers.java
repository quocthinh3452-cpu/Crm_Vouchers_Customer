package org.example.template_architecture.application.input;

import org.example.template_architecture.application.dto.response.UserResponse;
import java.util.List;

public interface IGetAllUsers {
    List<UserResponse> execute();
}
