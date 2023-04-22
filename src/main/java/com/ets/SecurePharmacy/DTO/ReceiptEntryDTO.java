package com.ets.SecurePharmacy.DTO;

import java.util.List;

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
public class ReceiptEntryDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("entryDate")
	private String entryDate;
	@JsonProperty("referenceNumber")
	private String referenceNumber;
	@JsonProperty("amountType")
	private String amountType;
	@JsonProperty("refernceDate")
	private String refernceDate;
	@JsonProperty("amount")
	private Double amount;
	@JsonProperty("remarks")
	private String remarks;
	@JsonProperty("totalAmount")
	private Double totalAmount;
	@JsonProperty("status")
	private String status;
	@JsonProperty("receiptDetailsEntity")
    List<ReceiptEntryDetailsDTO> receiptDetailsEntity;
}
