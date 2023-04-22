package com.ets.SecurePharmacy.DTO;

import java.util.List;

import com.ets.SecurePharmacy.tenant.model.CounterSaleDetailEntity;
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
public class CounterSaleDTO {


	@JsonProperty("id")
	private Long Id;
	
	@JsonProperty("entryDate")
	private String entryDate;
	@JsonProperty("totalItems")
	private Integer totalItems;
	@JsonProperty("totalVal")
	private Double totalVal;
	@JsonProperty("status")
	private String status;
	
	
	@JsonProperty("counteSaleDetails")
    List<CounterSaleDetailEntity> counteSaleDetails;

}
