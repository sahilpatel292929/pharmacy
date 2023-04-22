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
public class AccountTypeCreationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("account_type_name")
	private String accountTypeName;
	@JsonProperty("status")
	private String status;
}
