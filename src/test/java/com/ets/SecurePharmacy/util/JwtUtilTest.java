package com.ets.SecurePharmacy.util;

import com.ets.SecurePharmacy.master.model.Tenant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@ContextConfiguration(classes = {JwtUtil.class})
@ExtendWith(SpringExtension.class)
class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    private User user;

    @BeforeEach
    void setUp () {
        User userDetails = new User(
            "janedoe", "iloveyou", new ArrayList<>());
        com.ets.SecurePharmacy.master.model.User user = new com.ets.SecurePharmacy.master.model.User();
        user.setId(25L);
        Tenant tenant = new Tenant();
        tenant.setName("sample");
        user.setTenant(tenant);
        this.token = jwtUtil.generateToken(userDetails, user);
        this.user = userDetails;
    }

    @Test
    void testGetUsernameFromToken() {
        String result = jwtUtil.getUsernameFromToken(this.token);
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTenantName() {
        String result = jwtUtil.getTenantName(this.token);
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetUserId() {
        Integer result = jwtUtil.getUserId(this.token);
        Assertions.assertNotNull(result);
    }

    @Test
    void testValidateToken() {
        Boolean result = jwtUtil.validateToken(this.token, user);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result);
    }

    /**
     * Method under test: {@link JwtUtil#generateToken(UserDetails, com.ets.SecurePharmacy.master.model.User)}
     */
    @Test
    void testGenerateToken() {
        User userDetails = new User(
                "janedoe", "iloveyou", new ArrayList<>());
        com.ets.SecurePharmacy.master.model.User user = new com.ets.SecurePharmacy.master.model.User();
        user.setId(25L);
        Tenant tenant = new Tenant();
        tenant.setName("sample");
        user.setTenant(tenant);
        String result = jwtUtil.generateToken(userDetails, user);
        Assertions.assertNotNull(result);
    }
}

