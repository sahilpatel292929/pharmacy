package com.ets.SecurePharmacy.Controller;


import com.ets.SecurePharmacy.config.dbConfig.AsynceService;
import com.ets.SecurePharmacy.master.dao.TenantRepository;
import com.ets.SecurePharmacy.master.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reload")
public class ReloadConfigurationController {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private AsynceService asynceService;

    @PostMapping("/tenant-configuration")
    public Object reloadConfiguration(){
        List<Tenant> tenantList = tenantRepository.findAll();
        tenantList.parallelStream().forEach(tenant->{
            asynceService.updateTenant(tenant.getName());
        });
        return ResponseEntity.ok().body("success");
    }
}
