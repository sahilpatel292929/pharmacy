package com.ets.SecurePharmacy.tenant.model;

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
@Table(name="TBL_STOCK_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class StockCreationEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="stock_name",length=250)
	private String stockName;
	@Column(name="mode_status",length=10)
	private String status;
	@ManyToOne
    @JoinColumn(referencedColumnName = "ID")
	BranchCreationEntity barnchCreation;
	
	
}
