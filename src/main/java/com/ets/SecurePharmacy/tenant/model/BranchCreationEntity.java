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
@Table(name="TBL_BRANCH_CREATION")
@Getter
@Setter
@NoArgsConstructor
@Data
public class BranchCreationEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="branch_name",length=250)
	private String branchName;
	@Column(name="mobileno")
	private String mobileNo;
	@Column(name="addreess",length=250)
	private String addreess;
	@Column(name="location",length=250)
	private String location;
	@Column(name="contact_name",length=250)
	private String contact_name;
	
	@Column(name="dl_number")
	private String dlNumber;
	@Column(name="gst_number")
	private String gstNumber;
	@Column(name="pincode")
	private Integer pincode;
	@Column(name="status",length=10)
	private String status;
	
}
