package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.RegisterDTO;
import com.ets.SecurePharmacy.DTO.TenantCreationDTO;
import com.ets.SecurePharmacy.serviceimpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Object register(@Valid @RequestBody RegisterDTO registerDTO){
        return userService.register(registerDTO);
    }

    @GetMapping("getUser")
    public Object getUser(){
        return userService.getUser();
    }

    @PostMapping("addTenant")
    public Object addTenant(@Valid @RequestBody TenantCreationDTO tenantCreationDTO){
        return userService.addTenant(tenantCreationDTO);
    }

    @GetMapping("getTenant")
    public Object getTenant(){
        return userService.getTenant();
    }
}
