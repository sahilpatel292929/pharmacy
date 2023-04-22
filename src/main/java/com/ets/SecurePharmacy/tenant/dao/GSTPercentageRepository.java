package com.ets.SecurePharmacy.tenant.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.GSTPercentageEntity;


@Repository
public interface GSTPercentageRepository extends CrudRepository<GSTPercentageEntity, Long>{

}
