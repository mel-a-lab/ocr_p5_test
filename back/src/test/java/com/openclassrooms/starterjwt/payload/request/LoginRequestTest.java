package com.openclassrooms.starterjwt.payload.request;

import com.openclassrooms.starterjwt.payload.request.LoginRequest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LoginRequestTest {

    private Validator validator;

    public LoginRequestTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testGettersAndSetters() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        assertEquals("test@example.com", loginRequest.getEmail());
        assertEquals("password", loginRequest.getPassword());
    }

    @Test
    public void testEmailNotBlank() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("");
        loginRequest.setPassword("password");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<LoginRequest> violation : violations) {
            if ("email".equals(violation.getPropertyPath().toString())) {
                assertEquals("ne doit pas être vide", violation.getMessage());
            }
        }
    }

    @Test
    public void testPasswordNotBlank() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<LoginRequest> violation : violations) {
            if ("password".equals(violation.getPropertyPath().toString())) {
                assertEquals("ne doit pas être vide", violation.getMessage());
            }
        }
    }

    @Test
    public void testValidLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);
        assertTrue(violations.isEmpty());
    }
}
