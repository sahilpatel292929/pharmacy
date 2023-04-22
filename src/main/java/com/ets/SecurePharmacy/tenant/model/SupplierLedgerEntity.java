package com.ets.SecurePharmacy.tenant.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_SUPPLIER_LEDGER")
@Getter
@Setter
@Data
@NoArgsConstructor
public class SupplierLedgerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account_Name")
	private String accountName;
	
	@Column(name = "transaction_id")
	private String transactionId;
	@Column(name = "transaction_type")
	private String transactionType;
	@Column(name = "description")
	private String description;
	@Column(name = "add_amount")
	private Double addAmount;
	@Column(name = "less_amount")
	private Double lessAmount;
	@Column(name = "balance")
	private Double balance;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	SupplierCreationEntity supplierCreationEntity;
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Column(name="update_date")
	private LocalDateTime updatedDate;

}
