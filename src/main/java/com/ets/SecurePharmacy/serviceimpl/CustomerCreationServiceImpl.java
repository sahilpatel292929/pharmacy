package com.ets.SecurePharmacy.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.CutomerCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;
import com.ets.SecurePharmacy.service.CustomerCreationService;

@Service
public class CustomerCreationServiceImpl implements CustomerCreationService {

	@Autowired
	CutomerCreationRepository custRepositoy;

	public List<CustomerCreationEntity> getAllCutomer() {
		List<CustomerCreationEntity> result;
		try {
			result = (List<CustomerCreationEntity>) custRepositoy.findAll();
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<CustomerCreationEntity>();
		}

	}

	public CustomerCreationEntity getCustomerById(Long id) throws RecordNotFoundException {
		Optional<CustomerCreationEntity> customer;
		try {
			customer = custRepositoy.findById(id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (customer.isPresent()) {
			return customer.get();
		} else {
			throw new RecordNotFoundException("No Customer record exist for given id");
		}
	}

	public CustomerCreationEntity createOrUpdateCustomer(CustomerCreationEntity entity) {
		entity.setCreated_date(LocalDate.now());
		entity.setCreated_by("admin");
		entity.setUpdated_date(LocalDate.now());
		CustomerCreationEntity results;
		try {
			results = custRepositoy.findByMobileNo(entity.getMobileNo());
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (results!= null) {
			throw new RecordAlreadyPresentException("Customer Name Already Created");
		} else {
			entity = custRepositoy.save(entity);
		}

		return entity;
	}

	public void deleteCustomerById(Long id) throws RecordNotFoundException {
		Optional<CustomerCreationEntity> customer;
		try {
			customer = custRepositoy.findById(id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (customer.isPresent()) {
			custRepositoy.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Customer record exist for given id");
		}
	}

	public CustomerCreationEntity getCustomerByMobile(String mobile) {
		// TODO Auto-generated method stub
		CustomerCreationEntity cus;
		try {
			cus = custRepositoy.findByMobileNo(mobile);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return cus;
	}

	@Override
	public void createOrUpdateCustomerList(List<CustomerCreationEntity> entity) {

		try {
			custRepositoy.saveAll(entity);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

	}

	@Override
	public Page<CustomerCreationEntity> getAllCustomersWithPagination(int offset, int pageSize) {
		Page<CustomerCreationEntity> result;
		try {
			result = custRepositoy.findAll(PageRequest.of(offset, pageSize));
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;
	}

	@Override
	public Page<CustomerCreationEntity> getSearch(String query, Pageable pageable) {
		Page<CustomerCreationEntity> result;
		try {
			result = custRepositoy.findAllByCustomerNameOrMobileNoContains(query, query,pageable);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;
	}

	@Override
	public CustomerCreationEntity updateCustomer(CustomerCreationEntity entity) {
		entity.setCreated_date(LocalDate.now());
		entity.setCreated_by("admin");
		entity.setUpdated_date(LocalDate.now());
		try {
			entity = custRepositoy.save(entity);
		}
		catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
		return entity;
	}

}
