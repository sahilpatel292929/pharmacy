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
@Table(name="TBL_OPENING_STOCK_ENTRY_DETAIL")
@Getter
@Setter
@Data
@NoArgsConstructor
@Transactional
public class OpenStockEntryDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long Id;
	
	@Column(name="expairy_date")
	private LocalDate expairyDate;
	
	@Column(name="mrp")
	private Double mrp;
	
	@Column(name="purchase_rate")
	private Double purchaseRate;
	
	@Column(name="qty")
	private Double qty;
	
	
	@Column(name="batch_No")
	private String batchNo;
	
	@Column(name="srt")
	private Double srt;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="qpk")
	private Double qpk;
	
	
	@JsonBackReference 
	@ManyToOne(optional= false)
	@JoinColumn(referencedColumnName = "id", nullable= false)
	OpenStockEntryEntity openStockEntry;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	ItemCreationEntity items;

	
	 
	
}
