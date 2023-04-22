package com.ets.SecurePharmacy.tenant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_ACCOUNT_TYPE_CREATION")
@Getter
@Setter
@NoArgsConstructor
public class AccountTypeCreationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "account_type_name", length = 250)
	private String accountTypeName;
	@Column(name = "mode_status", length = 10)
	private String status;

	@Override
	public String toString() {
		return "AccountTypeCreationEntity [id=" + id + ", accountTypeName=" + accountTypeName + ", status=" + status
				+ "]";
	}

}
