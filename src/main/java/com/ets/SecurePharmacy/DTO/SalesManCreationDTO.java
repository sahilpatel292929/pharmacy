package com.ets.SecurePharmacy.DTO;

import com.ets.SecurePharmacy.tenant.model.AreaCreationEntity;
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
public class SalesManCreationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("salesManName")
	private String salesManName;
	@JsonProperty("incentive")
	private Integer incentive;
	@JsonProperty("target")
	private String target;
	@JsonProperty("status")
	private String status;
	@JsonProperty("areaCreation")
	AreaCreationEntity areaCreation;
}
