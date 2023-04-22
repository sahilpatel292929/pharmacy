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
public class OrderGenerationDetailsDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("itemEntity")
	ItemCreationDTO itemEntity;
	@JsonProperty("orderQty")
	private Long orderQty;
	@JsonProperty("minQty")
	private Long minQty;
	@JsonProperty("maxQty")
	private Long maxQty;
	@JsonProperty("stock")
	private String stock;
	@JsonProperty("ySale")
	private Double ySale;
	@JsonProperty("wSale")
	private Double wSale;
	@JsonProperty("mSale")
	private Double mSale;
	@JsonProperty("pmSale")
	private Double pmSale;
	

}
