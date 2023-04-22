package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;

@Service
public interface GroupCreationService {
	public List<GroupCreationEntity> getAllGroupList();

	public GroupCreationEntity getGroupById(Long id) throws RecordNotFoundException;

	public boolean getGroupByName(String area);

	public GroupCreationEntity createOrUpdateGroup(GroupCreationEntity entity);
	
	public GroupCreationEntity updateGroup(GroupCreationEntity entity);

	public void createOrUpdateGroup(List<GroupCreationEntity> entity);

	public void deleteGroupById(Long id) throws RecordNotFoundException;

	public Page<GroupCreationEntity> getAllGroupWithPagination(int offset, int pageSize);

	public Page<GroupCreationEntity> getSearch(String query, Pageable pageable);
}
