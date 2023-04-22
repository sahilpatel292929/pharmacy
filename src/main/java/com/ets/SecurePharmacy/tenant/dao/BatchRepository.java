package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ets.SecurePharmacy.DTO.BatchDetailsDTO;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;

@Repository
public interface BatchRepository extends JpaRepository<BatchEntity, Long> {
	
	@Query("SELECT i FROM BatchEntity i WHERE CONCAT(i.id) LIKE %?1%")
	public List<BatchEntity> search(String keyword);
	
	Page<BatchEntity> findAllByIdContains(String name, Pageable pageable);
	
	@Query(value="SELECT * FROM TBL_BATCH_CREATION where batch_no =?1 and items_id=?2",  nativeQuery = true)
	public BatchEntity getMRPand(String batch_no,Long item_id);
	
	@Query(value="SELECT * FROM TBL_BATCH_CREATION where items_id=?1",nativeQuery = true)
	public List<BatchEntity> getListBatches(Long id);
	
	@Query(value="SELECT * FROM TBL_BATCH_CREATION where items_id=?1",nativeQuery = true)
	public List<String> getListBatchNos(Long id);
	
	
	@Query(value="SELECT * FROM TBL_BATCH_CREATION where batch_no=?1",nativeQuery = true)
	public BatchEntity getBatchDetails(String batch_no);
	
	
	@Query(value="SELECT bc.* FROM TBL_BATCH_CREATION bc inner join TBL_BATCH_DETAIL_CREATION bcd on bcd.batchEntity_id= bc.id where bcd.batch_inv_no = ?1",nativeQuery = true)
	public BatchEntity getBatchInvoiceNo(Long batch_inv_no);
	
	@Modifying
	@Transactional
	//@Query(value="UPDATE TBL_BATCH_DETAIL_CREATION bdc SET bdc.status = :status, bdc.avialble_stock=: avialble_stock WHERE bdc.batch_inv_no = :batch_inv_no ", nativeQuery = true)
	//void updateStatus(@Param("status") String status, @Param("avialble_stock") Long  avialble_stock,@Param("batch_inv_no") Long batch_inv_no);
	@Query(value="UPDATE TBL_BATCH_DETAIL_CREATION bdc SET bdc.status = ?1, bdc.avialble_stock=?2 WHERE bdc.batch_inv_no =?3 ", nativeQuery = true)
	void updateStatus(String status,  Double  avialble_stock,Long batch_inv_no);

	
	 @Query(nativeQuery = true,value = "SELECT * FROM securepharma_admin.TBL_BATCH_CREATION  bc inner join TBL_BATCH_DETAIL_CREATION bcd where bc.id=bcd.batchEntity_id and items_id=?1")
	 List<BatchDetailsDTO> getAllBatchDetailsByItemId(Long id);
	 
	 public static interface BatchDetailsDTO {

	     String getAvialble_stock();

	     String getBatch_no();
	     
	     String getSupplierDetails_id();
	     
	     String getExpiry();
	     
	     String getBatch_inv_no();
	     
	     String getMrp();
	     
	     String getSale_rate();
	     
	  }
}
