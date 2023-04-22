package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.BranchCreationEntity;

@Repository
public interface BranchCreationRepository extends JpaRepository<BranchCreationEntity, Long> {

	List<BranchCreationEntity> findByBranchName(String bName);

	@Query("SELECT i FROM BranchCreationEntity i WHERE CONCAT(i.branchName) LIKE %?1%")
	public List<BranchCreationEntity> search(String keyword);

	@Query("SELECT i FROM BranchCreationEntity i WHERE CONCAT(i.branchName) LIKE %?1%")
	Page<BranchCreationEntity> findAllByBranchContains(String name, Pageable pageable);
}
