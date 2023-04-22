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
public class SaleReportSupplierWiseDTO {

	@JsonProperty("id")
    private Long id;
    
	@NotNull
	@JsonProperty("item_name")
    private String itemName;
    
    @JsonProperty("qty")
    private String qty;
    
    @JsonProperty("sale_value")
    private String saleValue;
	
    
	@NotNull
	@JsonProperty("current_stock")
    private String currentStock;
    
    @JsonProperty("stock_value")
    private String stockValue;
    
    @JsonProperty("suppplier_name")
    private String supplierName;
    
    @JsonProperty("group_name")
    private String groupName;
    
    @JsonProperty("mfg_name")
    private String mfgName;
    
	
}
