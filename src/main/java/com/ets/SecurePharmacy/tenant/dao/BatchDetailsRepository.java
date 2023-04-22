package com.ets.SecurePharmacy.tenant.dao;

import com.ets.SecurePharmacy.DTO.BatchDetailsDTO;
import com.ets.SecurePharmacy.tenant.model.BatchDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchDetailsRepository extends JpaRepository<BatchDetailsEntity,Long> {
    @Query(nativeQuery = true,value = "SELECT * from TBL_BATCH_DETAIL_CREATION tbdc where batchEntity_id  = ?1 ORDER by id DESC limit 1")
    BatchDetailsEntity getLastBatchDetailsEntityByBatchId(Long batchId);
    
   
    
}


