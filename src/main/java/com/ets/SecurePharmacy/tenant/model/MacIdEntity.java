package com.ets.SecurePharmacy.tenant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_MAC_ID_DETAILS")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MacIdEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="mac_id")
	private String macId;
	@Column(name="hdd_id")
	private String hddId;
	@Column(name="os_name")
	private String osName;
	
}
