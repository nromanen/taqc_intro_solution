package org.example;

import org.exception.EntityAlreadyExistsException;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private final Map<String, String> users;

    public AuthService() {
        users = new HashMap<>(Map.of("user1", "password1", "user2", "password1", "user3", "password2", "us", "pass"));
    }

    public AuthService(Map<String, String> users) {
        this.users = users;
    }

    public static byte[] getSHA(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public String registration(String login, String password) {
        if (users.containsKey(login)) {
            throw new EntityAlreadyExistsException("User with such login already exists");
        }
        if (password.matches(".*[A-Za-z\\d].*")) {
            return toHexString(getSHA(login));
        }
        throw new IllegalArgumentException("The password must be a combination of numbers, upper and lower letters, and special characters and at least 8 characters");
    }
}
