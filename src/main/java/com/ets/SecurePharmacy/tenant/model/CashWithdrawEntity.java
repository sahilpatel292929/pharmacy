package com.ets.SecurePharmacy.tenant.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_CASH_WITHDRAW")
@Getter
@Setter
@Data
@NoArgsConstructor
public class CashWithdrawEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(name = "entry_date")
	private LocalDate  entryDate;
	
	@Column(name = "bank_name")
	private String  bankName;
	
	@Column(name = "amount")
	private Double  amount;
	
	@Column(name = "status")
	private String  status;

	
}
