package com.codechallenge.product.uaa.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordUtil {

    private final PasswordEncoder passwordEncoder;

    public boolean matchPassword(String username, String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(prepareUserPassRawConcatenation(username, plainPassword), hashedPassword);
    }

    public String encodePassword(String username, String password) {
        return passwordEncoder.encode(prepareUserPassRawConcatenation(username, password));
    }

    //todo rename it to meaningful name
    private String prepareUserPassRawConcatenation(String username, String password) {
        final String SALT_NONCE = "c07610b3840c7e10798e";
        return String.format("%s-%s-%s", username, SALT_NONCE, password);
    }

}
