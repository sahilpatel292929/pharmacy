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
public class PackCreationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("packName")
	private String packName;
	@JsonProperty("qty")
	private int qty;
	@JsonProperty("status")
	private String status;
}
