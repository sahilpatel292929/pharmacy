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
public class CashWithdrawDTO {

	@JsonProperty("id")
	private Long Id;
	@JsonProperty("entryDate")
	private String  entryDate;
	
	@JsonProperty("bankName")
	private String  bankName;
	
	@JsonProperty("amount")
	private Double  amount;
	
	@JsonProperty("status")
	private String  status;


}
