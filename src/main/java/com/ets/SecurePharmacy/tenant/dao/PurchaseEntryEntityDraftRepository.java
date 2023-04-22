package com.ets.SecurePharmacy.tenant.dao;

import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntityDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseEntryEntityDraftRepository extends JpaRepository<PurchaseEntryEntityDraft,Long> {

    List<PurchaseEntryEntityDraft> findByCreatedUser(Long userId);

    void deleteByCreatedUser(Long userId);
}
