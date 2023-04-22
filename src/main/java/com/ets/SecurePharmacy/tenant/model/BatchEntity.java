package com.ets.SecurePharmacy.tenant.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_BATCH_CREATION")
@Getter
@Setter
@NoArgsConstructor
@Data
public class BatchEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	ItemCreationEntity items;
	
	@Column(name="expiry")
	private LocalDate expiry;
	
	
	@Column(name="qty_pr_pack")
	private Long qtyPerPack;
	
	@Column(name="batch_no")
	private String batchNo;
	
	@Column(name="mrp")
	private Double mrp;
	

	@Column(name="loose_mrp")
	private Double looseMRP;
	
	
	@Column(name="avialble_stock")
	private Double availableStock;
	
	@JsonManagedReference
	@OneToMany(
		    mappedBy = "batchEntity", 
		    cascade = CascadeType.ALL,
		    fetch=FetchType.EAGER)
    List<BatchDetailsEntity> batchDetails;

}
