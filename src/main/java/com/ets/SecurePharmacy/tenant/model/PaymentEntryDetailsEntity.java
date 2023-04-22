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
@Table(name="TBL_PAYMENT_ENTRY_DETAILS")
@Getter
@Setter
@NoArgsConstructor
@Data
@Transactional
public class PaymentEntryDetailsEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="entry_amount")
	private Double entryAmount;
	@Column(name="entry_discount")
	private Double entryDicount;
	@Column(name="net_amount")
	private Double netAmount;
	
	@ManyToOne
	@JoinColumn(name="account_id",referencedColumnName = "ID")
	AccountCreationEntity  accountEntity;
	
	
	@JsonBackReference
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(referencedColumnName="id")
	PaymentEntryEntity paymentEntryEntity;
}
