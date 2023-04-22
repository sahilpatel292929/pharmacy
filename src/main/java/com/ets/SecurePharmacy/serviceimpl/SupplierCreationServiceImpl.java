package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ets.SecurePharmacy.tenant.dao.SupplierCreationRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
import com.ets.SecurePharmacy.service.SupplierCreationService;

@Service("supplierService")
public class SupplierCreationServiceImpl implements SupplierCreationService {
	@Autowired
	SupplierCreationRepository supplierRepository;

	@Transactional
	public List<SupplierCreationEntity> getAllSuppliers() {
		List<SupplierCreationEntity> result ;
		try {
			result= (List<SupplierCreationEntity>) supplierRepository.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<SupplierCreationEntity>();
		}
	}

	@Transactional
	public SupplierCreationEntity getSupplierById(Long id) throws RecordNotFoundException {
		Optional<SupplierCreationEntity> supplier;
		try {
			supplier = supplierRepository.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (supplier.isPresent()) {
			return supplier.get();
		} else {
			throw new RecordNotFoundException("Supplier name is not available");
		}
	}

	@Transactional
	public SupplierCreationEntity createOrUpdateSupplier(SupplierCreationEntity entity) {
		/*
		 * entity.setCreated_date(new Date()); entity.setCreated_by("ADMIN");
		 * entity.setUpdated_date(new Date()); entity.setUpdated_by(0);
		 */
		try {
			entity = supplierRepository.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}

	@Transactional
	public void deleteSupplierById(Long id) throws RecordNotFoundException {
		Optional<SupplierCreationEntity> supplier;
		try {
			supplier = supplierRepository.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (supplier.isPresent()) {
			supplierRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("Supplier was nor Found");
		}

	}

	@Transactional
	public boolean getSupplierNameOrMobile(String sname, String mobile) {
		// TODO Auto-generated method stub
		List<SupplierCreationEntity> supRepo;
		
		try {
			 supRepo= supplierRepository.findBySupplierNameOrMobileNo(sname, mobile);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (supRepo.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public void createOrUpdateSupplierList(List<SupplierCreationEntity> entity) {
		// TODO Auto-generated method stub
		try {
			supplierRepository.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		

	}

	@Override
	@Transactional
	public Page<SupplierCreationEntity> getAllSuppliersWithPagination(int offset, int pageSize) {
		
		Page<SupplierCreationEntity> result;
		try{
			result = supplierRepository.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		

		return result;
	}

	@Override
	@Transactional
	public Page<SupplierCreationEntity> getSearch(String query, Pageable pageable) {
		Page<SupplierCreationEntity> result;
		try {
			result =  supplierRepository.findAllBySupplierNameOrMobileNoContains(query,query,pageable);	
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
			
		return result;
	}

	@Override
	@Transactional
	public SupplierCreationEntity updateSupplier(SupplierCreationEntity entity) {
		try {
			entity= supplierRepository.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}

}
