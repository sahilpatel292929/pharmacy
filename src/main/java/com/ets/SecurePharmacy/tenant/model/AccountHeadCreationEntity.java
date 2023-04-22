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
@Table(name="TBL_ACCOUNT_HEAD_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class AccountHeadCreationEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="account_head_name",length=250)
	private String accountHeadName;
	@Column(name="mode_status",length=10)
	private String status;
	
	
}
