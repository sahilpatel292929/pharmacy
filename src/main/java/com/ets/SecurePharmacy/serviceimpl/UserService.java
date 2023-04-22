package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.DTO.RegisterDTO;
import com.ets.SecurePharmacy.DTO.TenantCreationDTO;
import com.ets.SecurePharmacy.DTO.TenantDTO;
import com.ets.SecurePharmacy.DTO.UserResponseDTO;
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
import com.google.common.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final static String SUPER_ADMIN = "SUPER_ADMIN";
    private final static String ADMIN = "ADMIN";
    private final static String USER = "USER";


    @Value("${main.datasource.driver-class-name}")
    private String driver;

    @Value("${main.datasource.username}")
    private String userName;

    @Value("${main.datasource.password}")
    private String password;

    @Value("${DATABASE_COMMON_URL}")
    private String url;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AsynceService asynceService;

    @Autowired
    private TenantAdminMpgRepository tenantAdminMpgRepository;

    @Autowired
    private DTOTransfomers dtoTransfomers;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        // roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        // roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);

        System.out.println("admin=====>" + adminUser.getUserPassword());
        // userDao.save(adminUser);


//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Transactional(rollbackFor = {Exception.class, RecordAlreadyPresentException.class, UnableToProcessException.class, UserNotFoundException.class})
    public Object register(RegisterDTO registerDTO) {

        // current login user details
        String userName = BaseMethod.getPrinciple();
        User currentUser = userDao.findByUserName(userName);
        List<String> roleList = currentUser.getRole().stream().map(Role::getRoleName).collect(Collectors.toList());


        User user = userDao.findByUserName(registerDTO.getUserName());
        if (user != null) {
            throw new RecordAlreadyPresentException("user is already register");
        }
        user = new User();

        //role details set
        Set<Role> roleSet = new HashSet<>();
        Role role = roleDao.findByRoleName(registerDTO.getRole());
        if (role == null) {
            role = new Role();
            role.setRoleName(registerDTO.getRole());
        }
        roleSet.add(role);

        // user details set
        user.setUserName(registerDTO.getUserName());
        user.setUserFirstName(registerDTO.getUserFirstName());
        user.setUserLastName(registerDTO.getUserLastName());
        user.setUserPassword(passwordEncoder.encode(registerDTO.getUserPassword()));
        user.setRole(roleSet);
        user.setCreateUser(currentUser);
        user = userDao.save(user);

        //tenant details
        Tenant tenant = new Tenant();
        TenantAdminMpg tenantAdminMpg = new TenantAdminMpg();
        if (roleList.contains(SUPER_ADMIN) && Arrays.asList(ADMIN).contains(registerDTO.getRole())) {
            tenant = tenantRepository.findByName(registerDTO.getTenantName());
            if (tenant != null) {
                throw new RecordAlreadyPresentException("Tenant is already present");
            }

            asynceService.addTenant(registerDTO.getTenantName());
            tenant = createTenant(registerDTO.getTenantName(), tenant);
            user.setTenant(tenant);

            //save tenant admin save process
            tenantAdminMpg.setTenant(tenant);
            tenantAdminMpg.setAdminUser(user);
            tenantAdminMpgRepository.save(tenantAdminMpg);
        }

        if (roleList.contains(SUPER_ADMIN) && Arrays.asList(USER).contains(registerDTO.getRole())) {
            tenantAdminMpg = tenantAdminMpgRepository.findByTenantName(registerDTO.getTenantName());
            user.setTenantUser(tenantAdminMpg.getAdminUser());
            user.setTenant(tenant);
        }

        List<String> createdUserRoleList = currentUser.getCreateUser() != null ? currentUser.getCreateUser().getRole().stream().map(Role::getRoleName).collect(Collectors.toList()) : Arrays.asList(SUPER_ADMIN);

        if (roleList.contains(ADMIN) && createdUserRoleList.contains(SUPER_ADMIN) && Arrays.asList(ADMIN).contains(registerDTO.getRole())) {
            asynceService.addTenant(registerDTO.getTenantName());
            tenant = createTenant(registerDTO.getTenantName(), tenant);
            user.setTenant(tenant);
            user.setTenantUser(currentUser);
            //save tenant admin save process
            tenantAdminMpg.setTenant(tenant);
            tenantAdminMpg.setAdminUser(user);
            tenantAdminMpgRepository.save(tenantAdminMpg);
        }

        if (roleList.contains(ADMIN) && !createdUserRoleList.contains(SUPER_ADMIN) && Arrays.asList(ADMIN).contains(registerDTO.getRole())) {
            throw new UnableToProcessException("unable to process !!!!");
        }

        if (roleList.contains(ADMIN) && Arrays.asList(USER).contains(registerDTO.getRole())) {
            tenant = tenantRepository.findByName(registerDTO.getTenantName());
            if (tenant == null) {
                throw new UserNotFoundException("Tenant is not present with this name");
            }
            user.setTenantUser(currentUser);
            user.setTenant(tenant);
        }
        user = userDao.save(user);

        return new ResponseEntity<UserResponseDTO>(dtoTransfomers.entityToDto(user, UserResponseDTO.class), HttpStatus.OK);
    }

    public Object getUser() {
        String userName = BaseMethod.getPrinciple();
        User currentUser = userDao.findByUserName(userName);
        List<String> roleList = currentUser.getRole().stream().map(Role::getRoleName).collect(Collectors.toList());
        List<User> usersList = new ArrayList<>();
        if (roleList.contains(SUPER_ADMIN)) {
            usersList = userDao.findByTenantIsNotNull();
        } else {
            usersList = userDao.findByTenantUser(currentUser);
        }
        return usersList.stream().map(data -> {
            return dtoTransfomers.entityToDto(data, UserResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public Object addTenant(TenantCreationDTO tenantCreationDTO) {
        String userName = BaseMethod.getPrinciple();
        User currentUser = userDao.findByUserName(userName);
        List<String> roleList = currentUser.getRole().stream().map(Role::getRoleName).collect(Collectors.toList());

        Tenant tenant = null;
        TenantAdminMpg tenantAdminMpg = new TenantAdminMpg();

        asynceService.addTenant(tenantCreationDTO.getNewTenantName());

        tenant = createTenant(tenantCreationDTO.getNewTenantName(), tenant);

        tenantAdminMpg.setTenant(tenant);
        if (roleList.contains(SUPER_ADMIN)) {
            Integer userID = tenantRepository.getUserIdByTenantName(tenantCreationDTO.getExistingTenantName());
            User user = userDao.findById(userID).orElseThrow(() -> new UserNotFoundException("User not found!"));
            tenantAdminMpg.setAdminUser(user);
        } else {
            tenantAdminMpg.setAdminUser(currentUser);
        }

        tenantAdminMpgRepository.save(tenantAdminMpg);
        return new ResponseEntity<String>("tenant added successfully!!", HttpStatus.OK);
    }

    private Tenant createTenant(String tenantName, Tenant tenant) {
        tenant = new Tenant();
        String db_url = url + tenantName + "?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
        tenant.setName(tenantName);
        tenant.setDriverClassName(driver);
        tenant.setPassword(password);
        tenant.setUsername(this.userName);
        tenant.setUrl(db_url);
        return tenantRepository.save(tenant);
    }

    public Object getTenant() {
        String userName = BaseMethod.getPrinciple();
        User currentUser = userDao.findByUserName(userName);
        List<String> roleList = currentUser.getRole().stream().map(Role::getRoleName).collect(Collectors.toList());
        List<Tenant> tenantList = new ArrayList<>();
        if (roleList.contains(SUPER_ADMIN)) {
            tenantList = tenantRepository.findAll();
        } else {
            tenantList = tenantRepository.getTenantDetailsBasedAdminId(currentUser.getId());
        }
        return tenantList.stream().map(data -> {
            return dtoTransfomers.entityToDto(data, TenantDTO.class);
        }).collect(Collectors.toList());
    }
}