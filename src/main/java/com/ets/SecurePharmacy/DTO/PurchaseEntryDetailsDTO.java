package com.ets.SecurePharmacy.DTO;

import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class PurchaseEntryDetailsDTO {

	@JsonProperty("id")
	private Long Id;
	@JsonProperty("itemEntity")
	ItemCreationDTO itemEntity;
	@JsonProperty("invoiceNo")
	private String invoiceNo;
	@JsonProperty("batch")
	private String  batch;
	@JsonProperty("expiryDate")
	private String expiryDate;
	@JsonProperty("bestBefore")
	private String bestBefore;
	
	@JsonProperty("qty")
	private Double qty;
	
	@JsonProperty("freeQty")
	private Double freeQty;
	
	@JsonProperty("mrp")
	private Double mrp;
	@JsonProperty("purchaseRate")
	private Double purchaseRate;
	
	@JsonProperty("discount")
	private Double discount;
	
	@JsonProperty("discAmount")
	private Double discAmount;
	
	@JsonProperty("schdiscAmount")
	private Double schdiscAmount;
	
	@JsonProperty("gst")
	private Double gst;
	
	@JsonProperty("taxAmount")
	private Double taxAmount;
	
	@JsonProperty("qpk")
	private Double qpk;
	
	@JsonProperty("maxdate")
	private Double maxdate;
	
	@JsonProperty("srt")
	private Double srt;
	
	@JsonProperty("grossAmt")
	private Double grossAmt;
	
	@JsonProperty("netAmt")
	private Double netAmt;
	
	@JsonProperty("mfgdateFlag")
	private Boolean mfgdateFlag;
	@JsonProperty("purchaseEntryEntity")
	PurchaseEntryEntity purchaseEntryEntity;
}
