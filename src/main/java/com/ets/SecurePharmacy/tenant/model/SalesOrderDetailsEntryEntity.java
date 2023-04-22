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
@Table(name="TBL_SALES_ORDER_DETAILS_ENTRY")
@Setter
@Getter
@Data
@NoArgsConstructor
@Transactional
public class SalesOrderDetailsEntryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	
	@Column(name="qty")
	private double qty;
	
	@Column(name="status")
	private String status;
	
	@JsonBackReference
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(referencedColumnName="id")
	SalesOrderEntryEntity soEntryEntity;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(referencedColumnName = "ID")
	ItemCreationEntity items;
	
	
}
