package com.ets.SecurePharmacy.tenant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="TBL_RECEIPT_DETAILS_ENTRY")
@Setter
@Getter
@Data
@NoArgsConstructor
@Transactional
public class ReceiptEntryDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	

	@ManyToOne
	@JoinColumn(name="account_id",referencedColumnName = "ID")
	AccountCreationEntity  accountEntity;
	
	@Column(name="entry_amount")
	private Double entryAmount;
	@Column(name="disc_amount")
	private Double discAmount;
	@Column(name="net_amount")
	private Double netAmount;
	
	
	@JsonBackReference
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(referencedColumnName="id")
	ReceiptEntryEntity receiptEntity;
}
