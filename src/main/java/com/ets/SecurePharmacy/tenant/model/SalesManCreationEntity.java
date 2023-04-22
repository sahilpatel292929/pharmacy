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
@Table(name="TBL_SALES_MAN_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class SalesManCreationEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="saleman_name")
	private String salesManName;
	@Column(name="incentive")
	private int incentive;
	@Column(name="target")
	private String target;
	@Column(name="status")
	private String status;
	@ManyToOne
    @JoinColumn(referencedColumnName = "ID")
	AreaCreationEntity areaCreation;

	

	
}
