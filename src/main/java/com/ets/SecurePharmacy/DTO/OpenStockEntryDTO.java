package com.ets.SecurePharmacy.DTO;

import java.util.List;

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

public class OpenStockEntryDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("orderDate")
	private String orderDate;
	@JsonProperty("srt")
	private Double srt;
	@JsonProperty("totalItems")
	private Integer totalItems;
	@JsonProperty("mrpAmount")
	private Double mrpAmount;
	@JsonProperty("purchaseVal")
	private Double purchaseVal;
	@JsonProperty("salesVal")
	private Double salesVal;
	@JsonProperty("delStatus")
	private String delStatus;
	@JsonProperty("status")
	private String status;
	@JsonProperty("openStockDetails")
    List<OpenStockEntryDetailsDTO> openStockDetails;

}
