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

public class HsnSacCreationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("hsnName")
	private String hsnName;
	@JsonProperty("descirption")
	private String descirption;
	@JsonProperty("status")
	private String status;
}
