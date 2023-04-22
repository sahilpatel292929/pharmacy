package com.ets.SecurePharmacy.DTO;

import com.ets.SecurePharmacy.tenant.model.BranchCreationEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class StockCreationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("stockName")
	private String stockName;
	@JsonProperty("status")
	private String status;
	@JsonProperty("barnchCreation")
	BranchCreationEntity barnchCreation;
	
}
