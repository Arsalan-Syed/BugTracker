package com.github.syed.bugtracker.user;

import com.github.syed.bugtracker.CreateUserRequestValidator;
import com.github.syed.bugtracker.auth.LoginRequest;
import com.github.syed.bugtracker.auth.LoginResponse;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {

    private final CreateUserRequestValidator validator;
    private final UserService service;

    public AuthenticationController(CreateUserRequestValidator validator, UserService service) {
        this.validator = validator;
        this.service = service;
    }

    @PostMapping(value = "/register")
    public LoginResponse createUser(@Valid @RequestBody CreateUserRequest request){
        return service.createUser(request);
    }

    @PostMapping(value = "/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return service.login(loginRequest);
    }

    @InitBinder("createUserRequest")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}