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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_SALES_ORDER_ENTRY")
@Setter
@Getter
@Data
@NoArgsConstructor
@Transactional
public class SalesOrderEntryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="cus_mobile_no")
	private Long customerMobileNo;
	@Column(name="customer_name")
	private String customerName;
	@Column(name="customer_addresss")
	private String customerAddress;
	@Column(name="so_date")
	private LocalDate soDate;
	@Column(name="delivery_date")
	private LocalDate deliveryDate;
	@Column(name="total_count")
	private Long totalCount;
	@Column(name="total_qty")
	private Long totalQty;
	@Column(name="status")
	private String status;

	@JsonManagedReference
	@OneToMany(
		    mappedBy = "soEntryEntity", 
		    cascade = CascadeType.ALL,
		    fetch=FetchType.EAGER)
    List<SalesOrderDetailsEntryEntity> salesOrderDetailsEntity;
	
}
