package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.AreaCreationEntity;

@Repository
public interface AreaCreationRepository extends JpaRepository<AreaCreationEntity, Long> {

	List<AreaCreationEntity> findByAreaName(String area);
}
