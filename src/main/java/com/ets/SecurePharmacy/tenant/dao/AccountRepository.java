package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.AccountEntity;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity,Long> {

	List<AccountEntity> findAll();

}
