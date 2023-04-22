package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.service.ImportFileService;
import com.ets.SecurePharmacy.service.ItemCreationService;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImportFileServiceImpl implements ImportFileService {

    private static final String URL = "http://localhost:8081/item/import";
    @Autowired
    private ItemCreationService itemService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object getMappedItem(MultipartFile file) {
        List<ItemCreationEntity> lisItms = itemService.getAllItemList();
        Map<String,Long> dbItemName = lisItms.stream()
                .filter(data->data.getItemName()!=null && !data.getItemName().isEmpty())
                .collect(Collectors.toMap(ItemCreationEntity::getItemName,ItemCreationEntity::getId));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("itemName", dbItemName);

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        ResponseEntity<Object> response = restTemplate.postForEntity(URL, requestEntity, Object.class);

        return response;
    }
}
