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
public class DiscountSlabCreationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("discountSlabName")
	private String discountSlabName;
	@JsonProperty("discount")
	private String discount;
	@JsonProperty("from_amt")
	private Integer from_amt;
	@JsonProperty("to_amt")
	private Integer to_amt;
	
	@JsonProperty("status")
	private String status;
}
