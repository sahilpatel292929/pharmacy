package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
@Repository
public interface CompostionCreationRepository extends JpaRepository<CompostionCreationEntity, Long> {

	List<CompostionCreationEntity> findByCompName(String com);
	
	@Query("SELECT i FROM CompostionCreationEntity i WHERE CONCAT(i.compName) LIKE %?1%")
	public List<CompostionCreationEntity> search(String keyword);
	
	Page<CompostionCreationEntity> findAllByCompNameContains(String name, Pageable pageable);
	
}
