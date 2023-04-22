package com.ets.SecurePharmacy.tenant.dao;

import com.ets.SecurePharmacy.tenant.model.PurchaseEntryDetailsEntity;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PurchaseEntryDetailsRepository extends CrudRepository<PurchaseEntryDetailsEntity, Long>, QuerydslPredicateExecutor<PurchaseEntryDetailsEntity> {

}
