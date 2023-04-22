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
public class BoyCreationDTO {

	@JsonProperty("id")
	private Long Id;
	@JsonProperty("boyName")
	private String boyName;
	@JsonProperty("address1")
	private String mobileNo;
	@JsonProperty("address1")
	private String address1;
	@JsonProperty("address2")
	private String address2;
	@JsonProperty("salary")
	private String salary;
	@JsonProperty("status")
	private String status;
}
