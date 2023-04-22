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
@Table(name="TBL_PAYMENT_ENTRY")
@Getter
@Setter
@NoArgsConstructor
@Data
@Transactional
public class PaymentEntryEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="payment_date")
	private LocalDate paymentDate;
	@Column(name="reference_number")
	private String refernceNumber;
	@Column(name="reference_date")
	private LocalDate refernceDate;
	@Column(name="status")
	private String status;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="net_amount")
	private Double netAmount;
	
	@ManyToOne
	@JoinColumn(name="account_id",referencedColumnName = "ID")
	AccountCreationEntity  accountEntity;
	
	
	@JsonManagedReference
	@OneToMany(
		    mappedBy = "paymentEntryEntity", 
		    cascade = CascadeType.ALL,
		    fetch=FetchType.EAGER)
    List<PaymentEntryDetailsEntity> paymentEntryDetails;
	
}
	
