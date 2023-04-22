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
@Table(name="TBL_ACCOUNT")
@Getter
@Setter
@Data
@NoArgsConstructor
public class AccountEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	 @Column(name="username")
	    private String userName;
	    
	 @Column(name="companyname")
	    private String companyName;
	 
	 @Column(name="address1")
	    private String address1;
	 

	 @Column(name="address2")
	    private String address2;
	 

	 @Column(name="postalcode")
	    private String postalCode;
	 

	 @Column(name="mobilenumber")
	    private String mobileNumber;
	    

	 @Column(name="emailid")
	    private String emailId;
	    

	 @Column(name="logourl")
	    private String logoUrl;
	    
	    
	    
}
