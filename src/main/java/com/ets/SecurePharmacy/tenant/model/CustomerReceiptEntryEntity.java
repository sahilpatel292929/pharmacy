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
@Table(name="TBL_CUSTOMER_RECEIPT_ENTRY")
@Setter
@Getter
@Data
@NoArgsConstructor
@Transactional
public class CustomerReceiptEntryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="reference_number")
	private String referenceNumber;
	@Column(name="entry_date")
	private LocalDate entryDate;
	
	@ManyToOne
	@JoinColumn(name="account_id",referencedColumnName = "ID")
	AccountCreationEntity  accountEntity;
	
	@Column(name="reference_date")
	private LocalDate refernceDate;
	
	@Column(name="remarks")
	private String remarks;
	@Column(name="total_amount")
	private Long totalAmount;
	@Column(name="status")
	private String status;

	@JsonManagedReference
	@OneToMany(
		    mappedBy = "customerReceiptEntity", 
		    cascade = CascadeType.ALL,
		    fetch=FetchType.EAGER)
    List<CustomerReceiptEntryDetailsEntity> customerReceiptDetailsEntity;
}
