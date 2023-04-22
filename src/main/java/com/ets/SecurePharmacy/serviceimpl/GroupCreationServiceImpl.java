package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.GroupCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.service.GroupCreationService;

@Service
public class GroupCreationServiceImpl implements GroupCreationService {

	@Autowired
	GroupCreationRepository gRepo;

	public List<GroupCreationEntity> getAllGroupList() {
		List<GroupCreationEntity> result;
		try {
			
			result = (List<GroupCreationEntity>) gRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<GroupCreationEntity>();
		}
	}

	public GroupCreationEntity getGroupById(Long id) throws RecordNotFoundException {
		Optional<GroupCreationEntity> group;
		try {
			group = gRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (group.isPresent()) {
			return group.get();
		} else {
			throw new RecordNotFoundException("No group record exist for given id");
		}
	}

	public boolean getGroupByName(String area) {
		List<GroupCreationEntity> gc;
		try {
			gc = gRepo.findByGroupName(area);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (gc.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public GroupCreationEntity createOrUpdateGroup(GroupCreationEntity entity) {
		List<GroupCreationEntity> gc ;
		try {
			gc = gRepo.findByGroupName(entity.getGroupName());
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (gc.size() > 0) {
			throw new RecordAlreadyPresentException("Group Name Already Created");
		} else {
			try {
				entity = gRepo.save(entity);
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
		}
		return entity;
	}

	public void deleteGroupById(Long id) throws RecordNotFoundException {
		Optional<GroupCreationEntity> area ;
		try {
			area = gRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (area.isPresent()) {
			gRepo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Group record exist for given id");
		}

	}

	@Override
	public void createOrUpdateGroup(List<GroupCreationEntity> entity) {
		// TODO Auto-generated method stub
		try {
			gRepo.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
	}

	@Override
	public Page<GroupCreationEntity> getAllGroupWithPagination(int offset, int pageSize) {
		Page<GroupCreationEntity> result ;
		try {
			
			result = gRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;

	}

	@Override
	public Page<GroupCreationEntity> getSearch(String query, Pageable pageable) {
	
		Page<GroupCreationEntity> result;
		try {
			result  = gRepo.findAllByGroupNameContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

	@Override
	public GroupCreationEntity updateGroup(GroupCreationEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity = gRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}

}
