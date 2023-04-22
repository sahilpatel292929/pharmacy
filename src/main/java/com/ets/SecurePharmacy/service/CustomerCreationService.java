package com.ets.SecurePharmacy.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;

@Service
public interface CustomerCreationService {

	public List<CustomerCreationEntity> getAllCutomer();
	public CustomerCreationEntity getCustomerById(Long id) throws RecordNotFoundException;
	public CustomerCreationEntity getCustomerByMobile(String mobile);
	public CustomerCreationEntity createOrUpdateCustomer(CustomerCreationEntity entity);
	public CustomerCreationEntity updateCustomer(CustomerCreationEntity entity);
	public void createOrUpdateCustomerList(List<CustomerCreationEntity> entity);
	public void deleteCustomerById(Long id) throws RecordNotFoundException;
	public Page<CustomerCreationEntity> getAllCustomersWithPagination(int offset,int pageSize);
	public Page<CustomerCreationEntity> getSearch(String query, Pageable pageable);

}
