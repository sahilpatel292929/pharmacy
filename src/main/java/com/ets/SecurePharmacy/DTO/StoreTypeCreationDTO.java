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
public class StoreTypeCreationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("storeTypeName")
	private String storeTypeName;
	@JsonProperty("status")
	private String status;
}
