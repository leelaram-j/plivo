package com.test.generateRequest;

import com.test.requestClasses.CreateUserRequest;

public class GenerateCreateUserRequest {
    public static CreateUserRequest generateRequest(String name, String email, String status, String gender) {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName(name);
        createUserRequest.setEmail(email);
        createUserRequest.setStatus(status);
        createUserRequest.setGender(gender);
        return createUserRequest;
    }
}
