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
@Table(name="TBL_BATCH_TRANSACTION_CREATION")
@Getter
@Setter
@NoArgsConstructor
@Data
public class BatchTranasactionEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="qty")
	private Long qty;
	
	@Column(name="batch_inv_no")
	private Long batchInvNo;
	
	@Column(name="created_date")
	private LocalDate createdDate; 
	
}
