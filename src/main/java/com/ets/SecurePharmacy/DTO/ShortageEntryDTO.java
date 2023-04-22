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
public class ShortageEntryDTO {

	@JsonProperty("id")
	private Long Id;
	@JsonProperty("entryDate")
	private String  entryDate;
	@JsonProperty("totalItems")
	private Double totalItems;
	@JsonProperty("totalQty")
	private Double totalQty;
	@JsonProperty("status")
	private String status;
	@JsonProperty("shortageDetails")
    List<ShortageEntryDetailsDTO> shortageDetails;
}
