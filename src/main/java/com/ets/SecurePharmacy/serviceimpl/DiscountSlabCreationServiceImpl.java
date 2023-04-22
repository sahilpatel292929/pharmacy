package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.DiscountSlabCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.service.DiscountSlabCreationService;

@Service
public class DiscountSlabCreationServiceImpl implements DiscountSlabCreationService {

	@Autowired
	DiscountSlabCreationRepository dscrepo;

	public List<DiscountSlabCreationEntity> getAllDisountSlabDetails() {
		List<DiscountSlabCreationEntity> result ;
		try {
			result = (List<DiscountSlabCreationEntity>) dscrepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<DiscountSlabCreationEntity>();
		}
	}

	public DiscountSlabCreationEntity getDiscountSalbById(Long id) throws RecordNotFoundException {
		Optional<DiscountSlabCreationEntity> dscId ;
		try {
			dscId = dscrepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (dscId.isPresent()) {
			return dscId.get();
		} else {
			throw new RecordNotFoundException("No Discount slab record exist for given id");
		}
	}

	public boolean getDiscountSlabName(String dname) {
		List<DiscountSlabCreationEntity> dsc ;
		try {
			dsc = dscrepo.findByDiscountSlabName(dname);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (dsc.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public DiscountSlabCreationEntity createOrUpdateDiscountSlab(DiscountSlabCreationEntity entity) {
		List<DiscountSlabCreationEntity> dsc ;
		try {
			dsc = dscrepo.findByDiscountSlabName(entity.getDiscountSlabName());
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
		if (dsc.size() > 0) {
			throw new RecordAlreadyPresentException("Discount Slab Name Already Created");
		}
		else {
			try {
				entity = dscrepo.save(entity);
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
			
		}
		
		return entity;
	}

	public void deleteDiscountSlabById(Long id) throws RecordNotFoundException {
		Optional<DiscountSlabCreationEntity> dsc ;
		try {
			dsc = dscrepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (dsc.isPresent()) {
			dscrepo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Discount record exist for given id");
		}
	}

	@Override
	public void createOrUpdateDiscountSlabList(List<DiscountSlabCreationEntity> entity) {
		// TODO Auto-generated method stub
		try {
			dscrepo.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
	}

	@Override
	public Page<DiscountSlabCreationEntity> getAllDiscountSlabWithPagination(int offset, int pageSize) {
		Page<DiscountSlabCreationEntity> result;
		try {
			result  = dscrepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;

	}

	@Override
	public Page<DiscountSlabCreationEntity> getSearch(String query, Pageable pageable) {
		Page<DiscountSlabCreationEntity> result;
		try {
			result = dscrepo.findAllByDiscountSlabNameContains(query, pageable);
			
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;

	}

	@Override
	public DiscountSlabCreationEntity updateDiscountSlab(DiscountSlabCreationEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity = dscrepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}

}
