package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.HsnSacCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.service.HsnSacCreationService;

@Service
public class HsnSacCreationServiceImpl implements HsnSacCreationService {

	@Autowired
	HsnSacCreationRepository hsnRepo;

	public List<HsnSacCreationEntity> getAllHSNSACList() {
		List<HsnSacCreationEntity> result;
		try {
			result = (List<HsnSacCreationEntity>) hsnRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<HsnSacCreationEntity>();
		}
	}

	public HsnSacCreationEntity getHsnById(Long id) throws RecordNotFoundException {
		Optional<HsnSacCreationEntity> hsn;
		try {
			hsn = hsnRepo.findById(id);
			
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (hsn.isPresent()) {
			return hsn.get();
		} else {
			throw new RecordNotFoundException("No HSN SAC record exist for given id");
		}
	}

	public boolean getHsnByName(String hsn) {
		List<HsnSacCreationEntity> hsnList;
		try {
			hsnList = hsnRepo.findByHsnName(hsn);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (hsnList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public HsnSacCreationEntity createOrUpdateHsn(HsnSacCreationEntity entity) {
		// TODO Auto-generated method stub
		List<HsnSacCreationEntity> hsnList;
		try {
			hsnList = hsnRepo.findByHsnName(entity.getHsnName());
		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (hsnList.size() > 0) {
			throw new RecordAlreadyPresentException("HSN Name Already Created");
		} else {
			try {
				entity = hsnRepo.save(entity);
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
			
		}
		
		return entity;
	}
	@Override
	public HsnSacCreationEntity updateHsn(HsnSacCreationEntity entity) {
		// TODO Auto-generated method stub
		
			try {
				entity = hsnRepo.save(entity);
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
			
		
		
		return entity;
	}
	public void deleteHsnById(Long id) throws RecordNotFoundException {
		Optional<HsnSacCreationEntity> hsnList;
		try {
			hsnList = hsnRepo.findById(id);
			
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (hsnList.isPresent()) {
			hsnRepo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No HSN SAC record exist for given id");
		}

	}

	@Override
	public void createOrUpdateHsnList(List<HsnSacCreationEntity> entity) {
		// TODO Auto-generated method stub
		
		try {
			hsnRepo.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

	}

	@Override
	public Page<HsnSacCreationEntity> getAllHsnSacWithPagination(int offset, int pageSize) {
		
		Page<HsnSacCreationEntity> result;
		try {
			result = hsnRepo.findAll(PageRequest.of(offset, pageSize));
		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return result;

	}

	@Override
	public Page<HsnSacCreationEntity> getSearch(String query, Pageable pageable) {
		Page<HsnSacCreationEntity> result ;
		try {
			result= hsnRepo.findAllByHsnNameContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

	

}
