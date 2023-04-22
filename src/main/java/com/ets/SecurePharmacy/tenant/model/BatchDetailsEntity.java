package com.ets.SecurePharmacy.tenant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_BATCH_DETAIL_CREATION")
@Getter
@Setter
@NoArgsConstructor
@Data

public class BatchDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "purchase_rate")
	private Double purchaseRate;
	
	@Column(name = "sale_rate")
	private Double SRT;

	@Column(name="loose_SRT")
	private Double looseSRT;
	
	@Column(name = "avialble_stock")
	private Double availableStock;

	@Column(name = "qty")
	private Double qty;
	
	@Column(name = "loose_qty")
	private Double looseQty;
	
	@Column(name = "loose_purchase_rate")
	private Double loosePurchaseRate;
	
	@Column(name = "netCost")
	private Double netCost;
	
	@Column(name = "loose_netCost")
	private Double looseNetCost;
	
	

	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	SupplierCreationEntity supplierDetails;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "batch_inv_no")
	private Long batchInvNo;

	@JsonBackReference
	@ManyToOne(optional = false)
	@JoinColumn(referencedColumnName = "id")
	BatchEntity batchEntity;

}
