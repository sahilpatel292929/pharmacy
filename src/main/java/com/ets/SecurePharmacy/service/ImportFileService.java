package com.ets.SecurePharmacy.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportFileService {
    Object getMappedItem(MultipartFile file);
}
