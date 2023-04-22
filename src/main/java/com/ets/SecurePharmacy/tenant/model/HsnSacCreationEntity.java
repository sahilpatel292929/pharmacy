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
@Table(name="TBL_HSN_SAC_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class HsnSacCreationEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="hsn_name",length=250)
	private String hsnName;
	@Column(name="descirption")
	private String descirption;
	@Column(name="mode_status",length=10)
	private String status;
	
	
}
