package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.SalesManCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.SalesManCreationEntity;
import com.ets.SecurePharmacy.service.SalesManCreationService;

@Service
public class SalesManCreationServiceImpl implements SalesManCreationService {

	@Autowired
	SalesManCreationRepository salesManRepo;
	
	public List<SalesManCreationEntity> getAllSalesManDetails() {
		
		List<SalesManCreationEntity> result;
		try {
			 result = (List<SalesManCreationEntity>) salesManRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if(result.size() > 0) {
			return result;
		} 
		else {
			return new ArrayList<SalesManCreationEntity>();
		}
	}

	
	public SalesManCreationEntity getSalesManById(Long id) throws RecordNotFoundException {
		Optional<SalesManCreationEntity> salesMan;
		try {
			
			salesMan=salesManRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(salesMan.isPresent()) {
			return salesMan.get();
		}
		else {
			throw new RecordNotFoundException("No Saleman record exist for given id");
		}
	}

	
	public boolean getSalesManName(String sname) {
		
		List<SalesManCreationEntity> bStatus;
		try {
			bStatus=salesManRepo.findBySalesManName(sname);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if(bStatus.size()>0) {
			return true;
		}
		else {
			return false;
		}
	}

	
	public SalesManCreationEntity createOrUpdateSalesBoy(SalesManCreationEntity entity) {
		List<SalesManCreationEntity> bStatus=salesManRepo.findBySalesManName(entity.getSalesManName());
		if(bStatus.size()>0) {
			throw new RecordAlreadyPresentException("Sale Man Name Already Created");
		}
		else {
			try {
				entity=salesManRepo.save(entity);
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
		}
		
		return entity;
	}

	
	public void deleteSalesManById(Long id) throws RecordNotFoundException {
		Optional<SalesManCreationEntity> boy;
		try {
			boy=salesManRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(boy.isPresent()) {
			salesManRepo.deleteById(id);
		}
		else {
			throw new RecordNotFoundException("No SalesMan record exist for given id");
		}
		
	}


	@Override
	public void createOrUpdateSalesBoyList(List<SalesManCreationEntity> entity) {
		// TODO Auto-generated method stub
		try {
			salesManRepo.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
	}


	@Override
	public Page<SalesManCreationEntity> getAllSalesManWithPagination(int offset, int pageSize) {
		// TODO Auto-generated method stub
		Page<SalesManCreationEntity> result;
		try {
			result = salesManRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;


	}
	
	@Override
	public Page<SalesManCreationEntity> getSearch(String query, Pageable pageable) {
		Page<SalesManCreationEntity> result;
		try {
			result =  salesManRepo.findAllBySalesManNameContains(query,pageable);	
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}	
		return result;
	}


	@Override
	public SalesManCreationEntity updateSalesBoy(SalesManCreationEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity=salesManRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
		
	}
}
