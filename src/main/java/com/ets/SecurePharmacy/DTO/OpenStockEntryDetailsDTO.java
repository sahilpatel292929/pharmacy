package com.ets.SecurePharmacy.DTO;

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

public class OpenStockEntryDetailsDTO {

	@JsonProperty("id")
	private Long Id;
	@JsonProperty("itemEntity")
	ItemCreationDTO itemEntity;
	@JsonProperty("qty")
	private Double qty;
	@JsonProperty("schemeQty")
	private Double schemeQty;
	@JsonProperty("purchaseRate")
	private Double purchaseRate;
	@JsonProperty("discount")
	private Double discount;
	@JsonProperty("billDate")
	private String billDate;
	

}
