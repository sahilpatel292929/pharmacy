package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.service.ImportFileService;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/import")
public class ImportFileController {

    @Autowired
    private ImportFileService importFileService;

    @GetMapping("/item-mapped")
    @PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
    public Object getMappedItem(@RequestParam(name = "file") MultipartFile file) {
        return importFileService.getMappedItem(file);
    }
}
