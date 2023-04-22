package com.ets.SecurePharmacy.DTO;

import java.time.LocalDate;
import java.util.List;

import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;
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
public class SalesInvoiceDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("customerName")
	private String customerName;
	
	@JsonProperty( "customerAddress")
	private String customerAddress;
	
	@JsonProperty("doctor_name")
	private String doctor_name;
	
	@JsonProperty("doctorAddress")
	private String doctorAddress;
	
	@JsonProperty( "discount")
	private Double discount;
	

	@JsonProperty("remainderDays")
	private int remainderDays;
	
	@JsonProperty("remainder_date")
	private LocalDate remainderDate;
	
	@JsonProperty("homeDelivery")
	private String homeDelivery;
	
	@JsonProperty("billdate")
	private LocalDate billdate;
	
	@JsonProperty( "discountAmt")
	private Double discountAmt;
	
	@JsonProperty( "margin")
	private Double margin;
	
	@JsonProperty( "marginOn")
	private String marginOn;
	
	@JsonProperty( "grossAmount")
	private Double grossAmount;
	
	@JsonProperty("gst_amt")
	private Double gstAmt;
	
	
	@JsonProperty("roundOffAmt")
	private Double roundOffAmt;
	
	
	@JsonProperty("netAmt")
	private Double netAmt;
	
	@JsonProperty("otherExpenses")
	private String otherExpenses;
	
	@JsonProperty("expenses")
	private Double expenses;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("payment_mode")
	private String paymentMode;
	@JsonProperty("customerCreationEntity")
	CustomerCreationEntity customerCreationEntity;
	
	
	@JsonProperty("stockinvoiceDetails")
    List<SalesInvoiceEntryDetailDTO> stockinvoiceDetails;
}
