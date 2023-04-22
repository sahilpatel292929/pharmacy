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
public class SupplierCreationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("supplierName")
	private String supplierName;
	@JsonProperty("mobileNo")
	private String mobileNo;
	@JsonProperty("address1")
	private String address1;
	@JsonProperty("address2")
	private String address2;
	@JsonProperty("gstNo")
	private String gstNo;
	@JsonProperty("gstType")
	private String gstType;
	@JsonProperty("credit_days")
	private Integer credit_days;
	@JsonProperty("discount")
	private String discount;
	@JsonProperty("rateSlabrateSlab")
	private String rateSlab;
	@JsonProperty("modeOfPayment")
	private String modeOfPayment;
	@JsonProperty("openingBal")
	private Long openingBal;
	@JsonProperty("opening_Bal_date")
	private String openingBalDate;
	
	@JsonProperty("status")
	private String status;

}
