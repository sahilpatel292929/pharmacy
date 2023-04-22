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
@Table(name="TBL_GST_PERCENTAGE")
@Getter
@Setter
@Data
@NoArgsConstructor
public class GSTPercentageEntity {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(name="name")
	    private String name;
	 
	 @Column(name="value")
	    private String value;
	 
	 @Column(name="status")
	    private String status;
	 
}

