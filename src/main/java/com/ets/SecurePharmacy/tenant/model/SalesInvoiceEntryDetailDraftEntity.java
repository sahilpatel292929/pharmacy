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
@Table(name="TBL_SALES_INVOICE_DETAIL_DRAFT_ENTRY")
@Getter
@Setter
@Data
@NoArgsConstructor
@Transactional
public class SalesInvoiceEntryDetailDraftEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	
	@Column(name = "batch")
	private String batch;
	
	
	@Column(name = "expiry_date")
	private LocalDate expiryDate;
	
	
	@Column(name = "qty")
	private Double qty;
	
	
	@Column(name = "mrp")
	private Double mrp;
	
	@Column(name="sale_rate")
	private Double saleRate;
	
	@Column(name = "taxable_rate")
	private Double taxbleRate;
	
	@Column(name = "taxable_amount")
	private Double taxbleAmount;
	
	@Column(name = "discount")
	private Double discount;
	
	@Column(name = "discount_amount")
	private Double discountAmt;
	
	@Column(name = "gst")
	private Integer gst;
	
	@Column(name = "tax_amount")
	private Double taxAmount;	
	
	
	@Column(name = "net_amt")
	private Double netAmt;

	
	@Column(name = "gross_amt")
	private Double grossAmt;
	
	@Column(name = "margin_amount")
	private Double marginAmount;
	
	@Column(name = "net_cost")
	private Double netCost;
	
	@Column(name = "net_sale_rate")
	private Double netSaleRate;
	
	@Column(name = "margin_percentage")
	private Double marginPercentage;
	
	@JsonBackReference 
	@ManyToOne(optional= false)
	@JoinColumn(referencedColumnName = "id")
	SalesInvoiceDraftEntity saleInvEntryDraftEntity;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	ItemCreationEntity items;
	
}