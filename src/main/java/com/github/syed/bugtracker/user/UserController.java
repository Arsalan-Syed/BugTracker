package com.github.syed.bugtracker.user;

import com.github.syed.bugtracker.CreateUserRequestValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final CreateUserRequestValidator validator;
    private final UserService service;

    public UserController(CreateUserRequestValidator validator, UserService service) {
        this.validator = validator;
        this.service = service;
    }

    @PostMapping(value = "/register")
    public void createUser(@Valid @RequestBody CreateUserRequest request){
        service.createUser(request);
    }

    @PostMapping(value = "/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest){
        return service.login(loginRequest);
    }

    @InitBinder("createUserRequest")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}