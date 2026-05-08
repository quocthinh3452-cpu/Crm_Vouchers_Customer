package org.example.template_architecture.presentation.controller;

import jakarta.validation.Valid;
import org.example.template_architecture.application.dto.request.UserRequest;
import org.example.template_architecture.application.dto.response.UserResponse;
import org.example.template_architecture.application.input.ICreateUser;
import org.example.template_architecture.application.input.IGetAllUsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final ICreateUser createUser;
    private final IGetAllUsers getAllUsers;

    public UserController(ICreateUser createUser, IGetAllUsers getAllUsers) {
        this.createUser = createUser;
        this.getAllUsers = getAllUsers;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(getAllUsers.execute());
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        UserResponse response = createUser.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}