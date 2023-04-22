package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.PackCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.PackCreationEntity;
import com.ets.SecurePharmacy.service.PackCreationService;

@Service
public class PackCreationServiceImpl implements PackCreationService {

	@Autowired
	PackCreationRepository pRepo;

	public List<PackCreationEntity> getAllPackList() {
		List<PackCreationEntity> result ;
		try {
			result= (List<PackCreationEntity>) pRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<PackCreationEntity>();
		}
	}

	public PackCreationEntity getPakById(Long id) throws RecordNotFoundException {
		Optional<PackCreationEntity> pack ;
		try {
			pack = pRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (pack.isPresent()) {
			return pack.get();
		} else {
			throw new RecordNotFoundException("No Pack record exist for given id");
		}
	}

	public boolean getPackByName(String packs) {
		List<PackCreationEntity> pkg;
		try {
			pkg = pRepo.findByPackName(packs);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (pkg.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public PackCreationEntity createOrUpdatePack(PackCreationEntity entity) {
		List<PackCreationEntity> pkg ;
		try {
			pkg = pRepo.findByPackName(entity.getPackName());
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (pkg.size() > 0) {
			throw new RecordAlreadyPresentException("Pack Name Already Created");
		} else {
			try {
				entity = pRepo.save(entity);
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
		}
		return entity;
	}

	public void deletePackById(Long id) throws RecordNotFoundException {
		Optional<PackCreationEntity> pack;
		try {
			
			pack = pRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (pack.isPresent()) {
			pRepo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Pack record exist for given id");
		}
	}

	@Override
	public void createOrUpdatePack(List<PackCreationEntity> entity) {
		// TODO Auto-generated method stub
		try {
			pRepo.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
	}

	@Override
	public Page<PackCreationEntity> getAllPackWithPagination(int offset, int pageSize) {
		
		Page<PackCreationEntity> result;
		try {
			result = pRepo.findAll(PageRequest.of(offset, pageSize));

		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return result;

	}
	
	@Override
	public Page<PackCreationEntity> getSearch(String query, Pageable pageable) {
		Page<PackCreationEntity> result;
		try {
			result = pRepo.findAllByPackNameContains(query, pageable);

		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return result;
	}

	@Override
	public PackCreationEntity updatePack(PackCreationEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity = pRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}

}
