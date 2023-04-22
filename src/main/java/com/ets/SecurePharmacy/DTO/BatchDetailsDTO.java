package com.ets.SecurePharmacy.DTO;

import java.time.LocalDate;

import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDetailsDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("expiry")
	ItemCreationEntity items;
	
	@JsonProperty("expiry")
	private LocalDate expiry;
	
	
	@JsonProperty("qty_pr_pack")
	private Long qtyPerPack;
	
	@JsonProperty("batch_no")
	private String batchNo;
	
	@JsonProperty("mrp")
	private Double mrp;
	

	@JsonProperty("loose_mrp")
	private Double looseMRP;
	
	
	@JsonProperty("avialble_stock")
	private Double availableStock;
	
	@JsonProperty("purchase_rate")
	private Double purchaseRate;
	
	@JsonProperty("sale_rate")
	private Double SRT;

	@JsonProperty("loose_SRT")
	private Double looseSRT;
	
	@JsonProperty("avialble_batch_stock")
	private Double availableDetailsStock;

	@JsonProperty("qty")
	private Double qty;
	
	@JsonProperty("loose_qty")
	private Double looseQty;
	
	@JsonProperty("loose_purchase_rate")
	private Double loosePurchaseRate;
	
	@JsonProperty("netCost")
	private Double netCost;
	
	@JsonProperty("loose_netCost")
	private Double looseNetCost;
	

	@JsonProperty("expiry")
	SupplierCreationEntity supplierDetails;

	@JsonProperty("batch_inv_no")
	private Long batchInvNo;

	
}
