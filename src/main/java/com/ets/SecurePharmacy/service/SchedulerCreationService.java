package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;

@Service
public interface SchedulerCreationService {

	public List<SchedulerCreationEntity> getAllSchedulerList();

	public SchedulerCreationEntity getSchedulerById(Long id) throws RecordNotFoundException;

	public boolean getSchedulerByName(String area);

	public SchedulerCreationEntity createOrUpdateScheduler(SchedulerCreationEntity entity);
	
	public SchedulerCreationEntity updateScheduler(SchedulerCreationEntity entity);

	public void deleteSchedulerById(Long id) throws RecordNotFoundException;

	public Page<SchedulerCreationEntity> getAllSchedulerWithPagination(int offset, int pageSize);

	public Page<SchedulerCreationEntity> getSearch(String query, Pageable pageable);

}
