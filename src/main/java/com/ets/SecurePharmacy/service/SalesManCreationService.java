package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.SalesManCreationEntity;

@Service
public interface SalesManCreationService {

	public List<SalesManCreationEntity> getAllSalesManDetails();

	public SalesManCreationEntity getSalesManById(Long id) throws RecordNotFoundException;

	public boolean getSalesManName(String bname);

	public SalesManCreationEntity createOrUpdateSalesBoy(SalesManCreationEntity entity);
	
	public SalesManCreationEntity updateSalesBoy(SalesManCreationEntity entity);

	public void createOrUpdateSalesBoyList(List<SalesManCreationEntity> entity);

	public void deleteSalesManById(Long id) throws RecordNotFoundException;

	public Page<SalesManCreationEntity> getAllSalesManWithPagination(int offset, int pageSize);

	public Page<SalesManCreationEntity> getSearch(String query, Pageable pageable);
}
