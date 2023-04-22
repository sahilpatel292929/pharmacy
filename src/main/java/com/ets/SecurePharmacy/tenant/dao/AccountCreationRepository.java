package com.ets.SecurePharmacy.tenant.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
@Repository
public interface AccountCreationRepository extends JpaRepository<AccountCreationEntity, Long> {
	
	List<AccountCreationEntity> findByActName(String accountName);
	
	@Query("SELECT i FROM AccountCreationEntity i WHERE CONCAT(i.actName) LIKE %?1%")
	public List<AccountCreationEntity> search(String keyword);
	
	@Query("SELECT i FROM AccountCreationEntity i WHERE actType in ('Bank','Cash', 'Wallet') ")
	public List<AccountCreationEntity> paymentModes();
	
	
	@Query("SELECT i FROM AccountCreationEntity i WHERE actType in ('Bank') ")
	public List<AccountCreationEntity> bankNames();
	
    Page<AccountCreationEntity> findAllByActNameContains(String name, Pageable pageable);
	
}
