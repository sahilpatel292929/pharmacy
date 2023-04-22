package com.ets.SecurePharmacy.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.GSTTypeCreationEntity;

@Repository
public interface GSTTypeCreationRepository extends JpaRepository<GSTTypeCreationEntity, Long> {

}
