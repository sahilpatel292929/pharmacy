package com.ets.SecurePharmacy.tenant.model;

import java.time.LocalDate;

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

@Entity
@Table(name="TBL_STOCK_ADJ_DETAIL_ENTRY")
@Getter
@Setter
@Data
@NoArgsConstructor
@Transactional
public class StockAdjDetailEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	ItemCreationEntity items;
	
	@Column(name = "batch")
	private String batch;
	
	@Column(name = "current_stock")
	private Double currentStock;
	
	@Column(name = "available_qty")
	private Double availableQty;
	
	@Column(name = "agjusted_qty")
	private Double agjustedQty;
	
	@Column(name = "mrp")
	private Double mrp;
	
	@Column(name="expairy_date")
	private LocalDate expairy_date;
	@Column(name = "rate")
	private Double rate;

	@JsonBackReference 
	@ManyToOne(optional= false)
	@JoinColumn(referencedColumnName = "ID", nullable= false)
	StockAdjEntity stockAdjEntry;

	
	
}
