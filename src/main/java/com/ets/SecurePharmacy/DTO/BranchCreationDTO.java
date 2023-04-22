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
public class BranchCreationDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("branchName")
	private String branchName;
	@JsonProperty("mobileNo")
	private String mobileNo;
	@JsonProperty("addreess")
	private String addreess;
	@JsonProperty("location")
	private String location;
	@JsonProperty("contact_name")
	private String contact_name;
	@JsonProperty("dl_number")
	private String dlNumber;
	@JsonProperty("gst_number")
	private String gstNumber;
	@JsonProperty("pincode")
	private Integer pincode;
	
	@JsonProperty("status")
	private String status;
}
