package com.ets.SecurePharmacy.DTO;

import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
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
public class SalesInvoiceEntryDetailDTO {

	@JsonProperty("id")
	private Long Id;
	
	
	@JsonProperty("itemCreationEntity")
	ItemCreationEntity itemCreationEntity;
	
	@JsonProperty("batch")
	private String batch;
	
	@JsonProperty("batch_invoc_no")
	private Long batch_invoc_no;
	
	@JsonProperty("qty")
	private Double qty;
	
	@JsonProperty("mrp")
	private Double mrp;
	
	@JsonProperty("rate")
	private Double rate;
	
	@JsonProperty("discount")
	private Double discount;

	
	@JsonProperty("netAmt")
	private Double netAmt;

}
