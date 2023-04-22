package com.ets.SecurePharmacy.tenant.model;

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
@Table(name = "TBL_AREA_CREATION")
@Getter
@Setter
@NoArgsConstructor
@Data
public class AreaCreationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "area_name", length = 250)
	private String areaName;
	@Column(name = "route")
	private String route;
	@Column(name = "status", length = 10)
	private String status;

}
