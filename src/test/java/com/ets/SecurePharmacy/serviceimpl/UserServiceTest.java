package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.DTO.RegisterDTO;
import com.ets.SecurePharmacy.DTO.TenantCreationDTO;
import com.ets.SecurePharmacy.config.dbConfig.AsynceService;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.master.dao.RoleRepository;
import com.ets.SecurePharmacy.master.dao.TenantAdminMpgRepository;
import com.ets.SecurePharmacy.master.dao.TenantRepository;
import com.ets.SecurePharmacy.master.dao.UserRepository;
import com.ets.SecurePharmacy.master.model.Role;
import com.ets.SecurePharmacy.master.model.Tenant;
import com.ets.SecurePharmacy.master.model.TenantAdminMpg;
import com.ets.SecurePharmacy.master.model.User;
import com.ets.SecurePharmacy.transfomers.DTOTransfomers;
import com.ets.SecurePharmacy.util.BaseMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private AsynceService asynceService;

    @MockBean
    private DTOTransfomers dTOTransfomers;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private TenantAdminMpgRepository tenantAdminMpgRepository;

    @MockBean
    private TenantRepository tenantRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#initRoleAndUser()}
     */
    @Test
    void testInitRoleAndUser() {
        // Arrange
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        // Act
        userService.initRoleAndUser();

        // Assert
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#initRoleAndUser()}
     */
    @Test
    void testInitRoleAndUser2() {
        // Arrange
        when(passwordEncoder.encode((CharSequence) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class, () -> userService.initRoleAndUser());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#registerNewUser(User)}
     */
    @Test
    void testRegisterNewUser() {
        // Arrange
        Role role = new Role();
        role.setId(123L);
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findById((String) any())).thenReturn(ofResult);
        when(userRepository.save((User) any())).thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

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

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class, () -> userService.registerNewUser(user14));
        verify(roleRepository).findById((String) any());
        verify(userRepository).save((User) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#registerNewUser(User)}
     */
    @Test
    void testRegisterNewUser2() {
        // Arrange
        Role role = new Role();
        role.setId(123L);
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findById((String) any())).thenReturn(ofResult);
        when(userRepository.save((User) any())).thenThrow(new RecordAlreadyPresentException("An error occurred"));
        when(passwordEncoder.encode((CharSequence) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

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

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class, () -> userService.registerNewUser(user14));
        verify(roleRepository).findById((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#registerNewUser(User)}
     */
    @Test
    void testRegisterNewUser3() {

        // Arrange
        when(roleRepository.findById((String) any())).thenReturn(Optional.of(new Role()));
        when(userRepository.save((User) any())).thenReturn(new User());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        User user14 = new User();
        user14.setId(123L);
        user14.setRole(new HashSet<>());
        user14.setUserFirstName("Jane");
        user14.setUserLastName("Doe");
        user14.setUserName("janedoe");
        user14.setUserPassword("iloveyou");

        // Act and Assert
        User user15 = userService.registerNewUser(user14);
        assertNotNull(user15);
    }

    /**
     * Method under test: {@link UserService#getEncodedPassword(String)}
     */
    @Test
    void testGetEncodedPassword() {
        // Arrange
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        // Act and Assert
        assertEquals("secret", userService.getEncodedPassword("iloveyou"));
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#getEncodedPassword(String)}
     */
    @Test
    void testGetEncodedPassword2() {
        // Arrange
        when(passwordEncoder.encode((CharSequence) any()))
                .thenThrow(new RecordAlreadyPresentException("An error occurred"));

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class, () -> userService.getEncodedPassword("iloveyou"));
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#register(RegisterDTO)}
     */
    @Test
    void testRegister() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User userWithRole = new User();
            Role role = new Role();
            userWithRole.setRole(Collections.singleton(role));
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userWithRole)
                .thenReturn(null);
            when(roleRepository.findByRoleName(Mockito.anyString()))
                .thenReturn(role);
            when(userRepository.save(Mockito.any(User.class))).thenReturn(userWithRole);
            // Arrange and Act
            Object result = userService.register(
                new RegisterDTO("Tenant Name", "janedoe", "Jane", "Doe", "iloveyou", "Role"));
            assertNotNull(result);
        }
    }

    @Test
    void testRegister2() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User userWithRole = new User();
            Role role = new Role();
            userWithRole.setRole(Collections.singleton(role));
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userWithRole)
                .thenReturn(userWithRole);
            when(roleRepository.findByRoleName(Mockito.anyString()))
                .thenReturn(role);
            when(userRepository.save(Mockito.any(User.class))).thenReturn(userWithRole);
            // Arrange and Act
            Assertions.assertThrows(RecordAlreadyPresentException.class, () -> userService.register(
                new RegisterDTO("Tenant Name", "janedoe", "Jane", "Doe", "iloveyou", "Role")));
        }
    }

    @Test
    void testRegister3() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User userWithRole = new User();
            Role role = new Role();
            role.setRoleName("SUPER_ADMIN");
            userWithRole.setRole(Collections.singleton(role));
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userWithRole)
                .thenReturn(null);
            when(roleRepository.findByRoleName(Mockito.anyString()))
                .thenReturn(role);
            when(this.tenantRepository.findByName(Mockito.anyString()))
                .thenReturn(null);
            Mockito.doNothing().when(asynceService).addTenant(Mockito.anyString());
            when(tenantAdminMpgRepository.save(Mockito.any(TenantAdminMpg.class))).thenReturn(new TenantAdminMpg());
            when(userRepository.save(Mockito.any(User.class))).thenReturn(userWithRole);
            // Arrange and Act
            Object result = userService.register(
                new RegisterDTO("Tenant Name", "janedoe", "Jane", "Doe", "iloveyou", "ADMIN"));
            assertNotNull(result);
        }
    }

    @Test
    void testRegister4() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User userWithRole = new User();
            Role role = new Role();
            role.setRoleName("SUPER_ADMIN");
            userWithRole.setRole(Collections.singleton(role));
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userWithRole)
                .thenReturn(null);
            when(roleRepository.findByRoleName(Mockito.anyString()))
                .thenReturn(role);
            when(this.tenantRepository.findByName(Mockito.anyString()))
                .thenReturn(new Tenant());
            Mockito.doNothing().when(asynceService).addTenant(Mockito.anyString());
            when(tenantAdminMpgRepository.save(Mockito.any(TenantAdminMpg.class))).thenReturn(new TenantAdminMpg());
            when(userRepository.save(Mockito.any(User.class))).thenReturn(userWithRole);
            // Arrange and Act
            Assertions.assertThrows(RecordAlreadyPresentException.class, () -> userService.register(
                new RegisterDTO("Tenant Name", "janedoe", "Jane", "Doe", "iloveyou", "ADMIN")));
        }
    }

    @Test
    void testRegister5() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User userWithRole = new User();
            Role role = new Role();
            role.setRoleName("SUPER_ADMIN");
            userWithRole.setRole(Collections.singleton(role));
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userWithRole)
                .thenReturn(null);
            when(roleRepository.findByRoleName(Mockito.anyString()))
                .thenReturn(null);
            when(this.tenantRepository.findByName(Mockito.anyString()))
                .thenReturn(new Tenant());
            Mockito.doNothing().when(asynceService).addTenant(Mockito.anyString());
            when(tenantAdminMpgRepository.save(Mockito.any(TenantAdminMpg.class))).thenReturn(new TenantAdminMpg());
            when(userRepository.save(Mockito.any(User.class))).thenReturn(userWithRole);
            when(tenantAdminMpgRepository.findByTenantName(Mockito.anyString())).thenReturn(new TenantAdminMpg());
            // Arrange and Act
            Object result =  userService.register(
                new RegisterDTO("Tenant Name", "janedoe", "Jane", "Doe", "iloveyou", "USER"));
            Assertions.assertNotNull(result);
        }
    }

    @Test
    void testRegister6() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User userWithRole = new User();
            Role role = new Role();
            role.setRoleName("ADMIN");
            User userWithSupreAdminRole = new User();
            Role superAdminRole = new Role();
            superAdminRole.setRoleName("SUPER_ADMIN");
            userWithRole.setRole(Collections.singleton(role));
            userWithSupreAdminRole.setRole(Collections.singleton(superAdminRole));
            userWithRole.setCreateUser(userWithSupreAdminRole);
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userWithRole)
                .thenReturn(null);
            when(roleRepository.findByRoleName(Mockito.anyString()))
                .thenReturn(null);
            when(this.tenantRepository.findByName(Mockito.anyString()))
                .thenReturn(null);
            Mockito.doNothing().when(asynceService).addTenant(Mockito.anyString());
            when(tenantAdminMpgRepository.save(Mockito.any(TenantAdminMpg.class))).thenReturn(new TenantAdminMpg());
            when(userRepository.save(Mockito.any(User.class))).thenReturn(userWithRole);
            when(tenantAdminMpgRepository.findByTenantName(Mockito.anyString())).thenReturn(new TenantAdminMpg());
            when(tenantRepository.save(Mockito.any(Tenant.class))).thenReturn(new Tenant());
            // Arrange and Act
            Object result =  userService.register(
                new RegisterDTO("Tenant Name", "janedoe", "Jane", "Doe", "iloveyou", "ADMIN"));
            Assertions.assertNotNull(result);
        }
    }

    @Test
    void testRegister7() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User userWithRole = new User();
            Role role = new Role();
            role.setRoleName("ADMIN");
            User userWithSupreAdminRole = new User();
            Role superAdminRole = new Role();
            superAdminRole.setRoleName("ADMIN");
            userWithRole.setRole(Collections.singleton(role));
            userWithSupreAdminRole.setRole(Collections.singleton(superAdminRole));
            userWithRole.setCreateUser(userWithSupreAdminRole);
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userWithRole)
                .thenReturn(null);
            when(roleRepository.findByRoleName(Mockito.anyString()))
                .thenReturn(null);
            when(this.tenantRepository.findByName(Mockito.anyString()))
                .thenReturn(null);
            Mockito.doNothing().when(asynceService).addTenant(Mockito.anyString());
            when(tenantAdminMpgRepository.save(Mockito.any(TenantAdminMpg.class))).thenReturn(new TenantAdminMpg());
            when(userRepository.save(Mockito.any(User.class))).thenReturn(userWithRole);
            when(tenantAdminMpgRepository.findByTenantName(Mockito.anyString())).thenReturn(new TenantAdminMpg());
            when(tenantRepository.save(Mockito.any(Tenant.class))).thenReturn(new Tenant());
            // Arrange and Act
            Assertions.assertThrows(UnableToProcessException.class, () -> userService.register(
                new RegisterDTO("Tenant Name", "janedoe", "Jane", "Doe", "iloveyou", "ADMIN")));
        }
    }

    @Test
    void testRegister8() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User userWithRole = new User();
            Role role = new Role();
            role.setRoleName("ADMIN");
            User userWithSupreAdminRole = new User();
            Role superAdminRole = new Role();
            superAdminRole.setRoleName("SUPER_ADMIN");
            userWithRole.setRole(Collections.singleton(role));
            userWithSupreAdminRole.setRole(Collections.singleton(superAdminRole));
            userWithRole.setCreateUser(userWithSupreAdminRole);
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userWithRole)
                .thenReturn(null);
            when(roleRepository.findByRoleName(Mockito.anyString()))
                .thenReturn(null);
            when(this.tenantRepository.findByName(Mockito.anyString()))
                .thenReturn(new Tenant());
            Mockito.doNothing().when(asynceService).addTenant(Mockito.anyString());
            when(tenantAdminMpgRepository.save(Mockito.any(TenantAdminMpg.class))).thenReturn(new TenantAdminMpg());
            when(userRepository.save(Mockito.any(User.class))).thenReturn(userWithRole);
            when(tenantAdminMpgRepository.findByTenantName(Mockito.anyString())).thenReturn(new TenantAdminMpg());
            when(tenantRepository.save(Mockito.any(Tenant.class))).thenReturn(new Tenant());
            // Arrange and Act
            Object result =  userService.register(
                new RegisterDTO("Tenant Name", "janedoe", "Jane", "Doe", "iloveyou", "USER"));
            Assertions.assertNotNull(result);
        }
    }

    @Test
    void testRegister9() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User userWithRole = new User();
            Role role = new Role();
            role.setRoleName("ADMIN");
            User userWithSupreAdminRole = new User();
            Role superAdminRole = new Role();
            superAdminRole.setRoleName("SUPER_ADMIN");
            userWithRole.setRole(Collections.singleton(role));
            userWithSupreAdminRole.setRole(Collections.singleton(superAdminRole));
            userWithRole.setCreateUser(userWithSupreAdminRole);
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userWithRole)
                .thenReturn(null);
            when(roleRepository.findByRoleName(Mockito.anyString()))
                .thenReturn(null);
            when(this.tenantRepository.findByName(Mockito.anyString()))
                .thenReturn(null);
            Mockito.doNothing().when(asynceService).addTenant(Mockito.anyString());
            when(tenantAdminMpgRepository.save(Mockito.any(TenantAdminMpg.class))).thenReturn(new TenantAdminMpg());
            when(userRepository.save(Mockito.any(User.class))).thenReturn(userWithRole);
            when(tenantAdminMpgRepository.findByTenantName(Mockito.anyString())).thenReturn(new TenantAdminMpg());
            when(tenantRepository.save(Mockito.any(Tenant.class))).thenReturn(new Tenant());
            // Arrange and Act
            Assertions.assertThrows(UserNotFoundException.class, () -> userService.register(
                new RegisterDTO("Tenant Name", "janedoe", "Jane", "Doe", "iloveyou", "USER")));
        }
    }

    /**
     * Method under test: {@link UserService#getUser()}
     */
    @Test
    void testGetUser() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("sample", "dummyone");
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        User currentUser = new User();
        currentUser.setRole(new HashSet<>());
        when(userRepository.findByUserName(Mockito.anyString())).thenReturn(currentUser);
        when(userRepository.findByTenantIsNotNull())
            .thenReturn(Collections.singletonList(new User()));
        when(userRepository.findByTenantUser(Mockito.any(User.class)))
            .thenReturn(Collections.singletonList(new User()));
        SecurityContextHolder.setContext(securityContext);
        // Arrange and Act
        Object result = userService.getUser();
        assertNotNull(result);

        Authentication authentication2 = new UsernamePasswordAuthenticationToken("sample", "dummyone");
        // Mockito.whens() for your authorization object
        SecurityContext securityContext2 = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext2.getAuthentication()).thenReturn(authentication2);
        User currentUser2 = new User();
        Role adminRole = new Role();
        adminRole.setRoleName("SUPER_ADMIN");
        currentUser2.setRole(Collections.singleton(adminRole));
        when(userRepository.findByUserName(Mockito.anyString())).thenReturn(currentUser2);
        SecurityContextHolder.setContext(securityContext2);
        // Arrange and Act
        Object result2 = userService.getUser();
        assertNotNull(result2);
    }

    /**
     * Method under test: {@link UserService#addTenant(TenantCreationDTO)}
     */
    @Test
    void testAddTenant() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User currentUser = new User();
            currentUser.setRole(new HashSet<>());
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(currentUser);
            Mockito.doNothing().when(asynceService).addTenant(Mockito.anyString());
            Mockito.when(tenantRepository.save(Mockito.any(Tenant.class))).thenReturn(new Tenant());
            when(tenantAdminMpgRepository.save(Mockito.any(TenantAdminMpg.class))).thenReturn(new TenantAdminMpg());
            // Arrange and Act
            Object result = userService.addTenant(new TenantCreationDTO("New Tenant Name", "Existing Tenant Name"));
            assertNotNull(result);
        }
    }

    /**
     * Method under test: {@link UserService#addTenant(TenantCreationDTO)}
     */
    @Test
    void testAddTenant2() {
        try (MockedStatic<BaseMethod> baseMethodMockedStatic = Mockito.mockStatic(BaseMethod.class)) {
            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("sample");
            User currentUser2 = new User();
            Role adminRole = new Role();
            adminRole.setRoleName("SUPER_ADMIN");
            currentUser2.setRole(Collections.singleton(adminRole));
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(currentUser2);
            Mockito.doNothing().when(asynceService).addTenant(Mockito.anyString());
            Mockito.when(tenantRepository.save(Mockito.any(Tenant.class))).thenReturn(new Tenant());
            when(tenantRepository.getUserIdByTenantName(Mockito.anyString())).thenReturn(1);
            when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new User()));
            when(tenantAdminMpgRepository.save(Mockito.any(TenantAdminMpg.class))).thenReturn(new TenantAdminMpg());
            // Arrange and Act
            Object result = userService.addTenant(new TenantCreationDTO("New Tenant Name", "Existing Tenant Name"));
            assertNotNull(result);

            baseMethodMockedStatic.when(BaseMethod::getPrinciple).thenReturn("");
            currentUser2 = new User();
            adminRole = new Role();
            adminRole.setRoleName("SUPER_ADMIN");
            currentUser2.setRole(Collections.singleton(adminRole));
            when(userRepository.findByUserName(Mockito.anyString())).thenReturn(currentUser2);
            Mockito.doNothing().when(asynceService).addTenant(Mockito.anyString());
            Mockito.when(tenantRepository.save(Mockito.any(Tenant.class))).thenReturn(new Tenant());
            when(tenantRepository.getUserIdByTenantName(Mockito.anyString())).thenReturn(1);
            when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
            when(tenantAdminMpgRepository.save(Mockito.any(TenantAdminMpg.class))).thenReturn(new TenantAdminMpg());
            // Arrange and Act
            Assertions.assertThrows(UserNotFoundException.class, () -> userService.addTenant(new TenantCreationDTO("New Tenant Name", "Existing Tenant Name")));
        }
    }

    /**
     * Method under test: {@link UserService#getTenant()}
     */
    @Test
    void testGetTenant() {

        Authentication authentication = new UsernamePasswordAuthenticationToken("sample", "dummyone");
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        User currentUser = new User();
        currentUser.setRole(new HashSet<>());
        when(userRepository.findByUserName(Mockito.anyString())).thenReturn(currentUser);
        when(tenantRepository.findAll())
            .thenReturn(Collections.singletonList(new Tenant()));
        when(tenantRepository.getTenantDetailsBasedAdminId(Mockito.anyLong()))
            .thenReturn(Collections.singletonList(new Tenant()));
        SecurityContextHolder.setContext(securityContext);
        // Arrange and Act
        Object result = userService.getTenant();
        assertNotNull(result);

        Authentication authentication2 = new UsernamePasswordAuthenticationToken("sample", "dummyone");
        // Mockito.whens() for your authorization object
        SecurityContext securityContext2 = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext2.getAuthentication()).thenReturn(authentication2);
        User currentUser2 = new User();
        Role adminRole = new Role();
        adminRole.setRoleName("SUPER_ADMIN");
        currentUser2.setRole(Collections.singleton(adminRole));
        when(userRepository.findByUserName(Mockito.anyString())).thenReturn(currentUser2);
        SecurityContextHolder.setContext(securityContext2);
        // Arrange and Act
        Object result2 = userService.getTenant();
        assertNotNull(result2);

    }
}

