package com.ets.SecurePharmacy.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

class PasswordGeneratorBcryptTest {
    /**
     * Method under test: {@link PasswordGeneratorBcrypt#passwordEncoder()}
     */
    @Test
    void testPasswordEncoder() {

        // Arrange and Act
        PasswordEncoder passwordEncoder = PasswordGeneratorBcrypt.passwordEncoder();

        Assertions.assertNotNull(passwordEncoder);
    }
}

