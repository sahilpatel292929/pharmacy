package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;

@Repository
public interface ManufacturerCreationRepository extends JpaRepository<ManufacturerCreationEntity, Long> {

	List<ManufacturerCreationEntity> findByManufacturerName(String mName);

	@Query("SELECT m FROM ManufacturerCreationEntity m WHERE CONCAT(m.manufacturerName) LIKE %?1%")
	public List<ManufacturerCreationEntity> search(String keyword);

	Page<ManufacturerCreationEntity> findAllByManufacturerNameContains(String name, Pageable pageable);

}