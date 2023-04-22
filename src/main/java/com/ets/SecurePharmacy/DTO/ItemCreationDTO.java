package com.ets.SecurePharmacy.DTO;

import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
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
public class ItemCreationDTO {

	@JsonProperty("id")
	private Long Id;
	@JsonProperty("itemName")
	private String itemName;
	
	@JsonProperty("packName")
	private String packName;
	
	@JsonProperty("qty_per_pack")
	private String qty_per_pack;
	
	@JsonProperty("min_amt")
	private double min_amt;
	
	@JsonProperty("max_amt")
	private double max_amt;
	
	@JsonProperty("min_qty")
	private double min_qty;
	
	@JsonProperty("max_qty")
	private double max_qty;
	
	@JsonProperty("gst")
	private Integer gst;
	
	@JsonProperty("rateA")
	private String rateA;
	
	@JsonProperty("rateB")
	private String rateB;
	
	@JsonProperty("rateC")
	private String rateC;
	
	@JsonProperty("rateD")
	private String rateD;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("manufactureEntity")
	ManufacturerCreationEntity manufactureEntity;
	
	@JsonProperty("groupEntity")
	GroupCreationEntity groupEntity;
	
	@JsonProperty("storeTypeEntity")
	StoreTypeCreationEntity storeTypeEntity;
	
	@JsonProperty("scheduleEntity")
	SchedulerCreationEntity scheduleEntity;
	
	@JsonProperty("compositionEntity")
	CompostionCreationEntity compositionEntity;
	
	@JsonProperty("discSalbEntity")
	DiscountSlabCreationEntity discSalbEntity;
	
	@JsonProperty("hsnsac")
	HsnSacCreationEntity hsnsac;
	
}
