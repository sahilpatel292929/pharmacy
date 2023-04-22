package com.ets.SecurePharmacy.tenant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author umapa
 *
 */
@Entity
@Table(name="TBL_COUNTER_SALE_DETAILS")
@Getter
@Data
@Setter
@NoArgsConstructor
@Transactional
public class CounterSaleDetailEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	ItemCreationEntity items;
	
	@Column(name="batch")
	private String batch;
	
	@Column(name="pack_qty")
	private Double packQty;
	
	@Column(name="loose_qty")
	private Double looseQty;
	
	@Column(name="total_qty")
	private Double totalQty;
	
	@Column(name="rate")
	private Double rate;
	
	@Column(name="amount")
	private Double amount;
	
	@JsonBackReference 
	@ManyToOne(optional= false)
	@JoinColumn(referencedColumnName = "ID", nullable= false)
	CounterSaleEntity counterSaleEntry;
	
}
