package com.ets.SecurePharmacy.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class StockAdjDetailDTO {

	@JsonProperty("id")
	private Long Id;
	
	@JsonProperty("itemEntity")
	ItemCreationDTO itemEntity;
	
	@JsonProperty("batch")
	private String batch;
	
	@JsonProperty("currentStock")
	private Double currentStock;
	
	@JsonProperty("agjustmentQty")
	private Double agjustmentQty;
	
	@JsonProperty("mrp")
	private Double mrp;
	
	@JsonProperty("rate")
	private Double rate;

	@JsonProperty("status")
	private String status;

}
