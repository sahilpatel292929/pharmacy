package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.BoyCreationEntity;

@Repository
public interface BoyCreationRepository extends CrudRepository<BoyCreationEntity, Long> {

	List<BoyCreationEntity> findByBoyName(String boyName);
}
