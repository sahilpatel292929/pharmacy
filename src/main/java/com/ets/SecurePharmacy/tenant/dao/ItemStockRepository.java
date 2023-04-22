package com.ets.SecurePharmacy.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.ItemStockEntity;

@Repository
public interface ItemStockRepository extends JpaRepository<ItemStockEntity, Long> {

//	@Query(value = "SELECT * FROM tbl_item_stock_entry it_stock WHERE it_stock.item_entity_id = ?", 
//			  nativeQuery = true)
	@Query(value="SELECT * FROM TBL_ITEM_STOCK_ENTRY WHERE lastUpdateTime IN (SELECT MAX( lastUpdateTime ) FROM TBL_ITEM_STOCK_ENTRY WHERE itemEntity_Id =?1 GROUP BY itemEntity_Id) ORDER BY itemEntity_Id ASC ", nativeQuery = true)
	public ItemStockEntity findByItemEntityId(Long item_id);
	//public ItemStockEntity findByItemEntityIdDesc(int item_id);
	
//	@Query("SELECT * FROM tbl_sales_order_entry so WHERE  so.id in (SELECT sdo.so_entry_entity_id FROM tbl_sales_order_details_entry sdo WHERE items_id = ?1)")
//	public List<ItemStockEntity> findByItemDetails(Long item_id);
}
