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
public class CustomerCreationDTO {

	@JsonProperty("id")
	private Long Id;
	
	@JsonProperty("customerName")
	private String customerName;
	
	@JsonProperty("mobileNo")
	private String mobileNo;
	
	@JsonProperty("address1")
	private String address1;
	
	@JsonProperty("address2")
	private String address2;
	
	@JsonProperty("gstType")
	private String gstType;
	
	@JsonProperty("gstNo")
	private String gstNo;
	
	@JsonProperty("discount")
	private Integer discount;
	
	@JsonProperty("rateSlab")
	private Integer rateSlab;
	
	@JsonProperty("modeOfPayment")
	private String modeOfPayment; 
	
	@JsonProperty("actName")
	private Integer creditLimit;
	
	@JsonProperty("dueDays")
	private Integer dueDays;
	
	@JsonProperty("openingBal")
	private Integer openingBal;
	
	@JsonProperty("openingBalDate")
	private String openingBalDate;
	
	@JsonProperty("status")
	private String status;
}
