package org.example;

import org.exception.EntityAlreadyExistsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private static AuthService authService;

    private static final String VALID_LOGIN = "Pas11_0A";

    @BeforeAll
    static void beforeAll() {
        authService = new AuthService();
    }

    @Test
    @DisplayName("When user with non-existing login and valid password registered then returns non-empty string")
    void registerUser() {
        String result = authService.registration("Other user", "qQ1!Qq!1");
        assertFalse(result.isEmpty(), "Register user method should return some non-empty string");
    }

    @Test
    @DisplayName("When user with some login already exists in store then EntityAlreadyExistsException is generated")
    void tryRegisterUserThatAlreadyExist() {
        EntityAlreadyExistsException thrown = assertThrows(EntityAlreadyExistsException.class, () -> {
            authService.registration("user1", VALID_LOGIN);
        });
        assertEquals("User with such login already exists", thrown.getMessage());
    }

    @Test
    @DisplayName("When user with empty login try to registered then IllegalArgumentException is generated")
    void tryRegisterWithEmptyLogin() {
        EntityAlreadyExistsException thrown = assertThrows(EntityAlreadyExistsException.class, () -> {
            authService.registration("", VALID_LOGIN);
        });
        assertEquals("The login can't be blank", thrown.getMessage());
    }

    @Test
    @DisplayName("When user with null as login try to registered then IllegalArgumentException is generated")
    void tryRegisterWithNullValueOfLogin() {
        EntityAlreadyExistsException thrown = assertThrows(EntityAlreadyExistsException.class, () -> {
            authService.registration(null, VALID_LOGIN);
        });
        assertEquals("The login can't be blank", thrown.getMessage());
    }

    @Test
    @DisplayName("When user without numbers in password try to registered then IllegalArgumentException is generated")
    void tryRegisterUserWithoutNumbersInPassword() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            authService.registration("Other user", "Aasd_)^%j");
        });
        assertEquals("The password must be a combination of numbers, upper and lower letters, and special characters and at least 8 characters", thrown.getMessage());
    }

    @Test
    @DisplayName("When user without  alphabetic symbols try to registered then IllegalArgumentException is generated")
    void tryRegisterUserWithoutAlphabeticSymbolsInPassword() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            authService.registration("Other user", "1234_)(*");
        });
        assertEquals("The password must be a combination of numbers, upper and lower letters, and special characters and at least 8 characters", thrown.getMessage());
    }

    @Test
    @DisplayName("When user without  special symbols try to registered then IllegalArgumentException is generated")
    void tryRegisterUserWithoutSpecialSymbolsInPassword() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            authService.registration("Other user", "1234Ashddlfk");
        });
        assertEquals("The password must be a combination of numbers, upper and lower letters, and special characters and at least 8 characters", thrown.getMessage());
    }

    @Test
    @DisplayName("When user with just 7 valid characters try to registered then IllegalArgumentException is generated")
    void tryRegisterUserWithJust7ValidSymbolsInPassword() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            authService.registration("Other user", "Aa1!_34");
        });
        assertEquals("The password must be a combination of numbers, letters, and special characters and at least 8 characters", thrown.getMessage());
    }

}
