package com.ets.SecurePharmacy.tenant.model;

import java.time.LocalDate;

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
@Table(name="TBL_CUSTOMER_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class CustomerCreationEntity {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long Id;
		@Column(name="customer_name")
		private String customerName;
		@Column(name="mobile_no")
		private String mobileNo;
		@Column(name="address1")
		private String address1;
		@Column(name="address2")
		private String address2;
		@Column (name="gst_type")
		private String gstType;
		@Column(name="gstno",length=15)
		private String gstNo;
		@Column(name="discount",length=25)
		private Integer discount;
		@Column (name="rate_slab", length=25)
		private Integer rateSlab;
		@Column(name="mode_of_payemnt")
		private String modeOfPayment; 
		@Column(name="credit_limit",length=25)
		private Integer creditLimit;
		@Column(name="due_days",length=25)
		private Integer dueDays;
		@Column(name="opening_bal",length=25)
		private Integer openingBal;
		@Column(name="opening_bal_date")
		private LocalDate openingBalDate;
		@Column(name = "created_by",updatable=false)
	    private String  created_by;
		@Column(name = "created_date" ,updatable=false)
		private LocalDate created_date;
		@Column(name = "updated_by")
		private String updated_by;
		@Column(name="updated_date")
		private LocalDate updated_date;
		
		@Column(name="status")
		private String status;
		
		

}
