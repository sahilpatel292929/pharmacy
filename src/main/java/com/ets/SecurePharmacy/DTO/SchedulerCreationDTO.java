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
public class SchedulerCreationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("schedulerName")
	private String schedulerName;
	@JsonProperty("waringMsg")
	private String waringMsg;
	@JsonProperty("warning")
	private String warning;
	@JsonProperty("status")
	private String status;
}
