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
public class OrderGenerationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("orderDate")
	private String orderDate;
	@JsonProperty("totalCount")
	private Long totalCount;
	@JsonProperty("orderQty")
	private Long orderQty;
	@JsonProperty("status")
	private String status;
	@JsonProperty("orderDetails")
    List<OrderGenerationDetailsDTO> orderDetails;

}
