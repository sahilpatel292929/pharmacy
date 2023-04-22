package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;

@Repository
public interface HsnSacCreationRepository extends JpaRepository<HsnSacCreationEntity, Long> {

	List<HsnSacCreationEntity> findByHsnName(String hsn);

	@Query("SELECT h FROM HsnSacCreationEntity h WHERE CONCAT(h.hsnName) LIKE %?1%")
	public List<HsnSacCreationEntity> search(String keyword);

	Page<HsnSacCreationEntity> findAllByHsnNameContains(String name, Pageable pageable);
}
