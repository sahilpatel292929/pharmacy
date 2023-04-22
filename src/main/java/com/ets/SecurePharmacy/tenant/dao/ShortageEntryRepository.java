package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.ShortageEntryEntity;

@Repository
public interface ShortageEntryRepository extends JpaRepository<ShortageEntryEntity, Long> {
	@Query("SELECT i FROM ShortageEntryEntity i WHERE CONCAT(i.entryDate) LIKE %?1%")
	public List<ShortageEntryEntity> search(String keyword);

	Page<ShortageEntryEntity> findAllByEntryDateContains(String name, Pageable pageable);

}
