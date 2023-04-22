package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;

@Repository
public interface SchedulerCreationRepository extends JpaRepository<SchedulerCreationEntity, Long> {

	List<SchedulerCreationEntity> findBySchedulerName(String sname);
	
	@Query("SELECT i FROM  SchedulerCreationEntity i WHERE CONCAT(i.schedulerName) LIKE %?1%")
	public List<SchedulerCreationEntity> search(String keyword);
	
    Page<SchedulerCreationEntity> findAllBySchedulerNameContains(String name, Pageable pageable);
	
}
