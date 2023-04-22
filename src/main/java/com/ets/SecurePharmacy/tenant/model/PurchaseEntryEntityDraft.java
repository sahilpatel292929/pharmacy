package com.ets.SecurePharmacy.tenant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="TBL_PURCHASE_ENTRY_DRAFT")
@Getter
@Data
@Setter
@NoArgsConstructor
@Transactional
public class PurchaseEntryEntityDraft {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "invoice_num")
	private String invoiceNo;
	@Column(name="supllier_name")
	private String supplierName;
	
	@Column(name = "invoice_date")
	private LocalDate  invoiceDate;
	@Column(name = "entry_date")
	private LocalDate entrydate;
	@Column(name = "discount")
	private Long discount;
	@Column(name = "duedays")
	private Integer dueday;
	@Column(name = "duedate")
	private LocalDate duedate;
	@Column(name = "srt_margin")
	private String srtMargin;
	@Column(name = "gross_amount")
	private Long grossAmounts;
	@Column(name = "discount_amt")
	private Double discAmount;
	@Column(name = "no_items")
	private Integer noItems;
	@Column(name = "gst_amount")
	private Long gstamount;
	@Column(name="round_amount")
	private Long roundAmount;
	@Column(name="footer_Date")
	private LocalDate footerDate;
	@Column(name="disPercentage")
	private Long disPercentage;
	@Column(name="netAmount")
	private Long netAmount;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	SupplierCreationEntity supplierDetails;
	
	@JsonManagedReference
	@OneToMany(
		    mappedBy = "purchaseEntryEntity", 
		    cascade = CascadeType.ALL,
		    fetch=FetchType.LAZY)
    List<PurchaseEntryDetailsEntityDraft> purchaseDetails;

	@Column(name = "created_user_id")
	private Long createdUser;
		
	
}
