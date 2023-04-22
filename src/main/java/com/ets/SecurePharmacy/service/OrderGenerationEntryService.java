package com.ets.SecurePharmacy.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.OrderGenerationEntity;

@Service
public interface OrderGenerationEntryService {

	public List<OrderGenerationEntity> getAll();

	public OrderGenerationEntity getById(Long id) throws RecordNotFoundException;

	public OrderGenerationEntity createOrUpdate(OrderGenerationEntity entity);

	
}
