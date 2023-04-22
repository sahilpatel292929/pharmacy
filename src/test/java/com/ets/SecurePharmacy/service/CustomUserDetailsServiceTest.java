package com.ets.SecurePharmacy.service;

import com.ets.SecurePharmacy.master.dao.UserRepository;
import com.ets.SecurePharmacy.master.model.Tenant;
import com.ets.SecurePharmacy.master.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomUserDetailsService.class})
@ExtendWith(SpringExtension.class)
class CustomUserDetailsServiceTest {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link CustomUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        // Arrange
        User user = new User();
        user.setCreateUser(new User());
        user.setId(123L);
        user.setRole(new HashSet<>());
        user.setTenant(new Tenant());
        user.setTenantUser(new User());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");

        Tenant tenant = new Tenant();
        tenant.setDriverClassName("Driver Class Name");
        tenant.setId(123L);
        tenant.setName("Name");
        tenant.setPassword("iloveyou");
        tenant.setUrl("https://example.org/example");
        tenant.setUsername("janedoe");

        User user1 = new User();
        user1.setCreateUser(new User());
        user1.setId(123L);
        user1.setRole(new HashSet<>());
        user1.setTenant(new Tenant());
        user1.setTenantUser(new User());
        user1.setUserFirstName("Jane");
        user1.setUserLastName("Doe");
        user1.setUserName("janedoe");
        user1.setUserPassword("iloveyou");

        User user2 = new User();
        user2.setCreateUser(user);
        user2.setId(123L);
        user2.setRole(new HashSet<>());
        user2.setTenant(tenant);
        user2.setTenantUser(user1);
        user2.setUserFirstName("Jane");
        user2.setUserLastName("Doe");
        user2.setUserName("janedoe");
        user2.setUserPassword("iloveyou");

        Tenant tenant1 = new Tenant();
        tenant1.setDriverClassName("Driver Class Name");
        tenant1.setId(123L);
        tenant1.setName("Name");
        tenant1.setPassword("iloveyou");
        tenant1.setUrl("https://example.org/example");
        tenant1.setUsername("janedoe");

        User user3 = new User();
        user3.setCreateUser(new User());
        user3.setId(123L);
        user3.setRole(new HashSet<>());
        user3.setTenant(new Tenant());
        user3.setTenantUser(new User());
        user3.setUserFirstName("Jane");
        user3.setUserLastName("Doe");
        user3.setUserName("janedoe");
        user3.setUserPassword("iloveyou");

        Tenant tenant2 = new Tenant();
        tenant2.setDriverClassName("Driver Class Name");
        tenant2.setId(123L);
        tenant2.setName("Name");
        tenant2.setPassword("iloveyou");
        tenant2.setUrl("https://example.org/example");
        tenant2.setUsername("janedoe");

        User user4 = new User();
        user4.setCreateUser(new User());
        user4.setId(123L);
        user4.setRole(new HashSet<>());
        user4.setTenant(new Tenant());
        user4.setTenantUser(new User());
        user4.setUserFirstName("Jane");
        user4.setUserLastName("Doe");
        user4.setUserName("janedoe");
        user4.setUserPassword("iloveyou");

        User user5 = new User();
        user5.setCreateUser(user3);
        user5.setId(123L);
        user5.setRole(new HashSet<>());
        user5.setTenant(tenant2);
        user5.setTenantUser(user4);
        user5.setUserFirstName("Jane");
        user5.setUserLastName("Doe");
        user5.setUserName("janedoe");
        user5.setUserPassword("iloveyou");

        User user6 = new User();
        user6.setCreateUser(user2);
        user6.setId(123L);
        user6.setRole(new HashSet<>());
        user6.setTenant(tenant1);
        user6.setTenantUser(user5);
        user6.setUserFirstName("Jane");
        user6.setUserLastName("Doe");
        user6.setUserName("janedoe");
        user6.setUserPassword("iloveyou");

        Tenant tenant3 = new Tenant();
        tenant3.setDriverClassName("Driver Class Name");
        tenant3.setId(123L);
        tenant3.setName("Name");
        tenant3.setPassword("iloveyou");
        tenant3.setUrl("https://example.org/example");
        tenant3.setUsername("janedoe");

        User user7 = new User();
        user7.setCreateUser(new User());
        user7.setId(123L);
        user7.setRole(new HashSet<>());
        user7.setTenant(new Tenant());
        user7.setTenantUser(new User());
        user7.setUserFirstName("Jane");
        user7.setUserLastName("Doe");
        user7.setUserName("janedoe");
        user7.setUserPassword("iloveyou");

        Tenant tenant4 = new Tenant();
        tenant4.setDriverClassName("Driver Class Name");
        tenant4.setId(123L);
        tenant4.setName("Name");
        tenant4.setPassword("iloveyou");
        tenant4.setUrl("https://example.org/example");
        tenant4.setUsername("janedoe");

        User user8 = new User();
        user8.setCreateUser(new User());
        user8.setId(123L);
        user8.setRole(new HashSet<>());
        user8.setTenant(new Tenant());
        user8.setTenantUser(new User());
        user8.setUserFirstName("Jane");
        user8.setUserLastName("Doe");
        user8.setUserName("janedoe");
        user8.setUserPassword("iloveyou");

        User user9 = new User();
        user9.setCreateUser(user7);
        user9.setId(123L);
        user9.setRole(new HashSet<>());
        user9.setTenant(tenant4);
        user9.setTenantUser(user8);
        user9.setUserFirstName("Jane");
        user9.setUserLastName("Doe");
        user9.setUserName("janedoe");
        user9.setUserPassword("iloveyou");

        Tenant tenant5 = new Tenant();
        tenant5.setDriverClassName("Driver Class Name");
        tenant5.setId(123L);
        tenant5.setName("Name");
        tenant5.setPassword("iloveyou");
        tenant5.setUrl("https://example.org/example");
        tenant5.setUsername("janedoe");

        User user10 = new User();
        user10.setCreateUser(new User());
        user10.setId(123L);
        user10.setRole(new HashSet<>());
        user10.setTenant(new Tenant());
        user10.setTenantUser(new User());
        user10.setUserFirstName("Jane");
        user10.setUserLastName("Doe");
        user10.setUserName("janedoe");
        user10.setUserPassword("iloveyou");

        Tenant tenant6 = new Tenant();
        tenant6.setDriverClassName("Driver Class Name");
        tenant6.setId(123L);
        tenant6.setName("Name");
        tenant6.setPassword("iloveyou");
        tenant6.setUrl("https://example.org/example");
        tenant6.setUsername("janedoe");

        User user11 = new User();
        user11.setCreateUser(new User());
        user11.setId(123L);
        user11.setRole(new HashSet<>());
        user11.setTenant(new Tenant());
        user11.setTenantUser(new User());
        user11.setUserFirstName("Jane");
        user11.setUserLastName("Doe");
        user11.setUserName("janedoe");
        user11.setUserPassword("iloveyou");

        User user12 = new User();
        user12.setCreateUser(user10);
        user12.setId(123L);
        user12.setRole(new HashSet<>());
        user12.setTenant(tenant6);
        user12.setTenantUser(user11);
        user12.setUserFirstName("Jane");
        user12.setUserLastName("Doe");
        user12.setUserName("janedoe");
        user12.setUserPassword("iloveyou");

        User user13 = new User();
        user13.setCreateUser(user9);
        user13.setId(123L);
        user13.setRole(new HashSet<>());
        user13.setTenant(tenant5);
        user13.setTenantUser(user12);
        user13.setUserFirstName("Jane");
        user13.setUserLastName("Doe");
        user13.setUserName("janedoe");
        user13.setUserPassword("iloveyou");

        User user14 = new User();
        user14.setCreateUser(user6);
        user14.setId(123L);
        user14.setRole(new HashSet<>());
        user14.setTenant(tenant3);
        user14.setTenantUser(user13);
        user14.setUserFirstName("Jane");
        user14.setUserLastName("Doe");
        user14.setUserName("janedoe");
        user14.setUserPassword("iloveyou");

        Tenant tenant7 = new Tenant();
        tenant7.setDriverClassName("Driver Class Name");
        tenant7.setId(123L);
        tenant7.setName("Name");
        tenant7.setPassword("iloveyou");
        tenant7.setUrl("https://example.org/example");
        tenant7.setUsername("janedoe");

        User user15 = new User();
        user15.setCreateUser(new User());
        user15.setId(123L);
        user15.setRole(new HashSet<>());
        user15.setTenant(new Tenant());
        user15.setTenantUser(new User());
        user15.setUserFirstName("Jane");
        user15.setUserLastName("Doe");
        user15.setUserName("janedoe");
        user15.setUserPassword("iloveyou");

        Tenant tenant8 = new Tenant();
        tenant8.setDriverClassName("Driver Class Name");
        tenant8.setId(123L);
        tenant8.setName("Name");
        tenant8.setPassword("iloveyou");
        tenant8.setUrl("https://example.org/example");
        tenant8.setUsername("janedoe");

        User user16 = new User();
        user16.setCreateUser(new User());
        user16.setId(123L);
        user16.setRole(new HashSet<>());
        user16.setTenant(new Tenant());
        user16.setTenantUser(new User());
        user16.setUserFirstName("Jane");
        user16.setUserLastName("Doe");
        user16.setUserName("janedoe");
        user16.setUserPassword("iloveyou");

        User user17 = new User();
        user17.setCreateUser(user15);
        user17.setId(123L);
        user17.setRole(new HashSet<>());
        user17.setTenant(tenant8);
        user17.setTenantUser(user16);
        user17.setUserFirstName("Jane");
        user17.setUserLastName("Doe");
        user17.setUserName("janedoe");
        user17.setUserPassword("iloveyou");

        Tenant tenant9 = new Tenant();
        tenant9.setDriverClassName("Driver Class Name");
        tenant9.setId(123L);
        tenant9.setName("Name");
        tenant9.setPassword("iloveyou");
        tenant9.setUrl("https://example.org/example");
        tenant9.setUsername("janedoe");

        User user18 = new User();
        user18.setCreateUser(new User());
        user18.setId(123L);
        user18.setRole(new HashSet<>());
        user18.setTenant(new Tenant());
        user18.setTenantUser(new User());
        user18.setUserFirstName("Jane");
        user18.setUserLastName("Doe");
        user18.setUserName("janedoe");
        user18.setUserPassword("iloveyou");

        Tenant tenant10 = new Tenant();
        tenant10.setDriverClassName("Driver Class Name");
        tenant10.setId(123L);
        tenant10.setName("Name");
        tenant10.setPassword("iloveyou");
        tenant10.setUrl("https://example.org/example");
        tenant10.setUsername("janedoe");

        User user19 = new User();
        user19.setCreateUser(new User());
        user19.setId(123L);
        user19.setRole(new HashSet<>());
        user19.setTenant(new Tenant());
        user19.setTenantUser(new User());
        user19.setUserFirstName("Jane");
        user19.setUserLastName("Doe");
        user19.setUserName("janedoe");
        user19.setUserPassword("iloveyou");

        User user20 = new User();
        user20.setCreateUser(user18);
        user20.setId(123L);
        user20.setRole(new HashSet<>());
        user20.setTenant(tenant10);
        user20.setTenantUser(user19);
        user20.setUserFirstName("Jane");
        user20.setUserLastName("Doe");
        user20.setUserName("janedoe");
        user20.setUserPassword("iloveyou");

        User user21 = new User();
        user21.setCreateUser(user17);
        user21.setId(123L);
        user21.setRole(new HashSet<>());
        user21.setTenant(tenant9);
        user21.setTenantUser(user20);
        user21.setUserFirstName("Jane");
        user21.setUserLastName("Doe");
        user21.setUserName("janedoe");
        user21.setUserPassword("iloveyou");

        Tenant tenant11 = new Tenant();
        tenant11.setDriverClassName("Driver Class Name");
        tenant11.setId(123L);
        tenant11.setName("Name");
        tenant11.setPassword("iloveyou");
        tenant11.setUrl("https://example.org/example");
        tenant11.setUsername("janedoe");

        User user22 = new User();
        user22.setCreateUser(new User());
        user22.setId(123L);
        user22.setRole(new HashSet<>());
        user22.setTenant(new Tenant());
        user22.setTenantUser(new User());
        user22.setUserFirstName("Jane");
        user22.setUserLastName("Doe");
        user22.setUserName("janedoe");
        user22.setUserPassword("iloveyou");

        Tenant tenant12 = new Tenant();
        tenant12.setDriverClassName("Driver Class Name");
        tenant12.setId(123L);
        tenant12.setName("Name");
        tenant12.setPassword("iloveyou");
        tenant12.setUrl("https://example.org/example");
        tenant12.setUsername("janedoe");

        User user23 = new User();
        user23.setCreateUser(new User());
        user23.setId(123L);
        user23.setRole(new HashSet<>());
        user23.setTenant(new Tenant());
        user23.setTenantUser(new User());
        user23.setUserFirstName("Jane");
        user23.setUserLastName("Doe");
        user23.setUserName("janedoe");
        user23.setUserPassword("iloveyou");

        User user24 = new User();
        user24.setCreateUser(user22);
        user24.setId(123L);
        user24.setRole(new HashSet<>());
        user24.setTenant(tenant12);
        user24.setTenantUser(user23);
        user24.setUserFirstName("Jane");
        user24.setUserLastName("Doe");
        user24.setUserName("janedoe");
        user24.setUserPassword("iloveyou");

        Tenant tenant13 = new Tenant();
        tenant13.setDriverClassName("Driver Class Name");
        tenant13.setId(123L);
        tenant13.setName("Name");
        tenant13.setPassword("iloveyou");
        tenant13.setUrl("https://example.org/example");
        tenant13.setUsername("janedoe");

        User user25 = new User();
        user25.setCreateUser(new User());
        user25.setId(123L);
        user25.setRole(new HashSet<>());
        user25.setTenant(new Tenant());
        user25.setTenantUser(new User());
        user25.setUserFirstName("Jane");
        user25.setUserLastName("Doe");
        user25.setUserName("janedoe");
        user25.setUserPassword("iloveyou");

        Tenant tenant14 = new Tenant();
        tenant14.setDriverClassName("Driver Class Name");
        tenant14.setId(123L);
        tenant14.setName("Name");
        tenant14.setPassword("iloveyou");
        tenant14.setUrl("https://example.org/example");
        tenant14.setUsername("janedoe");

        User user26 = new User();
        user26.setCreateUser(new User());
        user26.setId(123L);
        user26.setRole(new HashSet<>());
        user26.setTenant(new Tenant());
        user26.setTenantUser(new User());
        user26.setUserFirstName("Jane");
        user26.setUserLastName("Doe");
        user26.setUserName("janedoe");
        user26.setUserPassword("iloveyou");

        User user27 = new User();
        user27.setCreateUser(user25);
        user27.setId(123L);
        user27.setRole(new HashSet<>());
        user27.setTenant(tenant14);
        user27.setTenantUser(user26);
        user27.setUserFirstName("Jane");
        user27.setUserLastName("Doe");
        user27.setUserName("janedoe");
        user27.setUserPassword("iloveyou");

        User user28 = new User();
        user28.setCreateUser(user24);
        user28.setId(123L);
        user28.setRole(new HashSet<>());
        user28.setTenant(tenant13);
        user28.setTenantUser(user27);
        user28.setUserFirstName("Jane");
        user28.setUserLastName("Doe");
        user28.setUserName("janedoe");
        user28.setUserPassword("iloveyou");

        User user29 = new User();
        user29.setCreateUser(user21);
        user29.setId(123L);
        user29.setRole(new HashSet<>());
        user29.setTenant(tenant11);
        user29.setTenantUser(user28);
        user29.setUserFirstName("Jane");
        user29.setUserLastName("Doe");
        user29.setUserName("janedoe");
        user29.setUserPassword("iloveyou");

        User user30 = new User();
        user30.setCreateUser(user14);
        user30.setId(123L);
        user30.setRole(new HashSet<>());
        user30.setTenant(tenant7);
        user30.setTenantUser(user29);
        user30.setUserFirstName("Jane");
        user30.setUserLastName("Doe");
        user30.setUserName("janedoe");
        user30.setUserPassword("iloveyou");
        when(userRepository.findByUserName((String) any())).thenReturn(user30);

        // Act
        UserDetails actualLoadUserByUsernameResult = customUserDetailsService.loadUserByUsername("janedoe");

        // Assert
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByUserName((String) any());
    }
}

