package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.PurchaseEntrySettingEntity;
@Repository
public interface PurchaseEntrySettingRepo extends JpaRepository<PurchaseEntrySettingEntity, Long> {
	@Query("SELECT i FROM PurchaseEntrySettingEntity i WHERE CONCAT(i.settingName) LIKE %?1%")
	public List<PurchaseEntrySettingEntity> search(String keyword);

	Page<PurchaseEntrySettingEntity> findAllBySettingNameContains(String name, Pageable pageable);

	
}
