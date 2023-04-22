package com.ets.SecurePharmacy.DTO;

import javax.validation.constraints.NotNull;

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
public class AccountCreationDTO {

	@JsonProperty("id")
    private Long id;
    
	@NotNull
	@JsonProperty("actName")

    private String actName;
    
    @JsonProperty("actType")
    private String actType;
    
    @JsonProperty("actGroup")
    private String actGroup;
    @JsonProperty("openingBal")
    private String openingBal;
    @JsonProperty("openingBalDate")
    private String openingBalDate;
    @JsonProperty("status")
   	private String status;
    
   
}
