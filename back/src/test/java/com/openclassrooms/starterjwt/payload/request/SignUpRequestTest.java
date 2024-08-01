package com.openclassrooms.starterjwt.payload.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SignupRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsAreValid_thenNoViolations() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("First");
        signupRequest.setLastName("Last");
        signupRequest.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenEmailIsInvalid_thenViolation() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("invalid-email");
        signupRequest.setFirstName("First");
        signupRequest.setLastName("Last");
        signupRequest.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<SignupRequest> violation : violations) {
            if ("email".equals(violation.getPropertyPath().toString())) {
                assertEquals("doit être une adresse électronique syntaxiquement correcte", violation.getMessage());
            }
        }
    }

    @Test
    void whenFirstNameIsBlank_thenViolation() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("");
        signupRequest.setLastName("Last");
        signupRequest.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<SignupRequest> violation : violations) {
            if ("firstName".equals(violation.getPropertyPath().toString())) {
                assertFalse(violation.getMessage().contains("une autre chaîne de caractères"));
            }
        }
    }

    @Test
    void whenLastNameIsBlank_thenViolation() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("First");
        signupRequest.setLastName("");
        signupRequest.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<SignupRequest> violation : violations) {
            if ("lastName".equals(violation.getPropertyPath().toString())) {
                assertFalse(violation.getMessage().contains("une autre chaîne de caractères"));
            }
        }
    }

    @Test
    void whenPasswordIsTooShort_thenViolation() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("First");
        signupRequest.setLastName("Last");
        signupRequest.setPassword("short");

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<SignupRequest> violation : violations) {
            if ("password".equals(violation.getPropertyPath().toString())) {
                assertEquals("la taille doit être comprise entre 6 et 40", violation.getMessage());
            }
        }
    }

    @Test
    void whenPasswordIsTooLong_thenViolation() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("First");
        signupRequest.setLastName("Last");
        signupRequest.setPassword("thispasswordiswaytoolongtobevalidandshouldfail");

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<SignupRequest> violation : violations) {
            if ("password".equals(violation.getPropertyPath().toString())) {
                assertEquals("la taille doit être comprise entre 6 et 40", violation.getMessage());
            }
        }
    }

    @Test
    void whenFirstNameIsTooShort_thenViolation() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("Fi");
        signupRequest.setLastName("Last");
        signupRequest.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<SignupRequest> violation : violations) {
            if ("firstName".equals(violation.getPropertyPath().toString())) {
                assertEquals("la taille doit être comprise entre 3 et 20", violation.getMessage());
            }
        }
    }

    @Test
    void whenLastNameIsTooShort_thenViolation() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("First");
        signupRequest.setLastName("La");
        signupRequest.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<SignupRequest> violation : violations) {
            if ("lastName".equals(violation.getPropertyPath().toString())) {
                assertEquals("la taille doit être comprise entre 3 et 20", violation.getMessage());
            }
        }
    }

    @Test
    void testEqualsAndHashCode() {
        SignupRequest request1 = new SignupRequest();
        request1.setEmail("test@example.com");
        request1.setFirstName("First");
        request1.setLastName("Last");
        request1.setPassword("password123");

        SignupRequest request2 = new SignupRequest();
        request2.setEmail("test@example.com");
        request2.setFirstName("First");
        request2.setLastName("Last");
        request2.setPassword("password123");

        SignupRequest request3 = new SignupRequest();
        request3.setEmail("test@example.com");
        request3.setFirstName("First");
        request3.setLastName("DifferentLast");
        request3.setPassword("password123");

        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
        assertFalse(request1.equals(request3));
        assertFalse(request1.hashCode() == request3.hashCode());
    }

    @Test
    void testToString() {
        SignupRequest request = new SignupRequest();
        request.setEmail("test@example.com");
        request.setFirstName("First");
        request.setLastName("Last");
        request.setPassword("password123");

        String expectedToString = "SignupRequest(email=test@example.com, firstName=First, lastName=Last, password=password123)";
        assertEquals(expectedToString, request.toString());
    }

    @Test
    void whenFirstNameIsTooLong_thenViolation() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("ThisFirstNameIsWayTooLong");
        signupRequest.setLastName("Last");
        signupRequest.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<SignupRequest> violation : violations) {
            if ("firstName".equals(violation.getPropertyPath().toString())) {
                assertEquals("la taille doit être comprise entre 3 et 20", violation.getMessage());
            }
        }
    }

    @Test
    void whenLastNameIsTooLong_thenViolation() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("First");
        signupRequest.setLastName("ThisLastNameIsWayTooLong");
        signupRequest.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<SignupRequest> violation : violations) {
            if ("lastName".equals(violation.getPropertyPath().toString())) {
                assertEquals("la taille doit être comprise entre 3 et 20", violation.getMessage());
            }
        }
    }
}
