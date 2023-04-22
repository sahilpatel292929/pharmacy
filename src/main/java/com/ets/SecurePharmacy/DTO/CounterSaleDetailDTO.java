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
public class CounterSaleDetailDTO {

	@JsonProperty("id")
	private Long Id;
	
	
	@JsonProperty("itemEntity")
	ItemCreationDTO itemEntity;
	
	@JsonProperty("batch")
	private String batch;
	
	@JsonProperty("packQty")
	private Double packQty;
	
	@JsonProperty("looseQty")
	private Double looseQty;
	
	@JsonProperty("totalQty")
	private Double totalQty;
	
	@JsonProperty("rate")
	private Double rate;
	
	@JsonProperty("amount")
	private Double amount;
	

}
