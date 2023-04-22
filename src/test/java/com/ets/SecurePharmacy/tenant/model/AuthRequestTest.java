package com.ets.SecurePharmacy.tenant.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuthRequestTest {
    /**
     * Method under test: {@link AuthRequest#setUserName(String)}
     */
    @Test
    void testSetUserName() {
        // Arrange
        AuthRequest authRequest = new AuthRequest();

        // Act
        authRequest.setUserName("janedoe");

        // Assert
        assertEquals("janedoe", authRequest.getUserName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link AuthRequest}
     *   <li>{@link AuthRequest#setPassword(String)}
     *   <li>{@link AuthRequest#toString()}
     *   <li>{@link AuthRequest#getPassword()}
     *   <li>{@link AuthRequest#getUserName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        AuthRequest actualAuthRequest = new AuthRequest();
        actualAuthRequest.setPassword("iloveyou");
        String actualToStringResult = actualAuthRequest.toString();

        // Assert
        assertEquals("iloveyou", actualAuthRequest.getPassword());
        assertNull(actualAuthRequest.getUserName());
        assertEquals("AuthRequest [userName=null, password=iloveyou]", actualToStringResult);
    }
}

