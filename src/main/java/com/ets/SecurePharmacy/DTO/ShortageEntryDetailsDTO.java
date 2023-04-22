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
public class ShortageEntryDetailsDTO {

	@JsonProperty("id")
	private Long Id;
	
	@JsonProperty("itemEntity")
	ItemCreationDTO itemEntity;
	
	@JsonProperty("qty")
    private Double qty;
	
}
