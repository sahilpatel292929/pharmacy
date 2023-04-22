package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.PackCreationEntity;

@Repository
public interface PackCreationRepository extends JpaRepository<PackCreationEntity, Long> {

	List<PackCreationEntity> findByPackName(String pack);

	@Query("SELECT i FROM PackCreationEntity i WHERE CONCAT(i.packName) LIKE %?1%")
	public List<PackCreationEntity> search(String keyword);

	Page<PackCreationEntity> findAllByPackNameContains(String name, Pageable pageable);

}
