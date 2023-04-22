package com.ets.SecurePharmacy.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class StockAdjDTO {

	@JsonProperty("id")
	private Long Id;
	
	@JsonProperty("entryDate")
	private String entryDate;
	
	@JsonProperty("totalAdj")
	private String totalAdj;

	@JsonProperty("status")
	private String status;
	@JsonProperty("stockAdjDetails")
    List<StockAdjDetailDTO> stockAdjDetails;
}
