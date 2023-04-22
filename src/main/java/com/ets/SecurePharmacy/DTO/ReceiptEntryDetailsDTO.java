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
public class ReceiptEntryDetailsDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("amount")
	private Double amount;
	@JsonProperty("entryAmount")
	private Double entryAmount;
	@JsonProperty("discAmount")
	private Double discAmount;
	@JsonProperty("netAmount")
	private Double netAmount;
}
