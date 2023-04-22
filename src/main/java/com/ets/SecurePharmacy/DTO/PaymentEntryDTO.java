package com.ets.SecurePharmacy.DTO;

import java.util.List;

import com.ets.SecurePharmacy.tenant.model.PaymentEntryDetailsEntity;
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
public class PaymentEntryDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("paymentDate")
	private String paymentDate;
	@JsonProperty("refernceNumber")
	private String refernceNumber;
	@JsonProperty("refernceDate")
	private String refernceDate;
	@JsonProperty("status")
	private String status;
	@JsonProperty("paymentEntryDetails")
    List<PaymentEntryDetailsEntity> paymentEntryDetails;
}
