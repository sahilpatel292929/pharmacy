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

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_SALES_INVOICE_DRAFT_ENTRY")
@Getter
@Setter
@Data
@NoArgsConstructor
@Transactional
public class SalesInvoiceDraftEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name = "customer_address")
	private String customerAddress;
	
	@Column(name="doctor_name")
	private String doctor_name;
	
	@Column(name="doctor_address")
	private String doctorAddress;
	
	@Column(name = "discount")
	private Double discount;
	

	@Column(name="Remainder_days")
	private Integer RemainderDays;
	
	@Column(name="remainder_date")
	private LocalDate remainderDate;
	
	@Column(name="home_delivery")
	private String homeDelivery;
	
	@Column(name="bill_date")
	private LocalDate billdate;
	
	
	@Column(name = "discount_amt")
	private Double discountAmt;
	
	@Column(name = "margin")
	private Double margin;
	
	

	@Column(name = "margin_on")
	private String marginOn;
	
	
	@Column(name = "gross_amount")
	private Double grossAmount;
	
	
	@Column(name="gst_amt")
	private Double gstAmt;
	
	
	@Column(name="roud_off_amt")
	private Double roundOffAmt;
	
	
	@Column(name="net_amt")
	private Double netAmt;
	
	
	@Column(name="other_expenses")
	private String otherExpenses;
	
	
	@Column(name="expenses")
	private Double expenses;
	
	
	@Column(name="status")
	private String status;

	
	@Column(name="payment_status")
	private String paymentStatus; 
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	CustomerCreationEntity customerCreationEntity;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "saleInvEntryDraftEntity", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    List<SalesInvoiceEntryDetailDraftEntity> stockinvoiceDraftDetails;
	
	@Column(name = "created_user_id")
	private Long createdUser;
	
}
