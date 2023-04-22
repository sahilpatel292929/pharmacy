package com.ets.SecurePharmacy.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class PurchaseEntryDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("invoiceNo")
	private String invoiceNo;
	@JsonProperty("invoiceDate")
	private String  invoiceDate;
	@JsonProperty("entrydate")
	private String entrydate;
	@JsonProperty("discount")
	private Double discount;
	@JsonProperty("dueday")
	private Integer dueday;
	@JsonProperty("duedate")
	private String duedate;
	@JsonProperty("srtMargin")
	private String srtMargin;
	@JsonProperty("grossAmounts")
	private Double grossAmounts;
	@JsonProperty("discAmount")
	private double discAmount;
	@JsonProperty("noItems")
	private Integer noItems;
	@JsonProperty("gstamount")
	private Double gstamount;
	@JsonProperty("roundAmount")
	private Double roundAmount;
	@JsonProperty("footerDate")
	private String footerDate;
	@JsonProperty("disPercentage")
	private Double disPercentage;
	@JsonProperty("netAmount")
	private Double netAmount;
	@JsonProperty("status")
	private String status;
	@JsonProperty("purchaseDetails")
    List<PurchaseEntryDetailsDTO> purchaseDetails;
		
}
