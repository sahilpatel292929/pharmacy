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
@Table(name="TBL_SUPPLIER_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class SupplierCreationEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="supplier_name",length=250)
	private String supplierName;
	@Column(name="mobile_no",length=15)
	private String mobileNo;
	@Column(name="address1",length=1000)
	private String address1;
	@Column(name="address2",length=1000)
	private String address2;
	@Column(name="gstno",length=1000)
	private String gstNo;
	@Column(name="gst_type")
	private String gstType;
	@Column(name="credit_days")
	private Integer credit_days;
	@Column(name="discount")
	private String discount;
	@Column(name="rate_slab")
	private String rateSlab;
	@Column(name="mode_of_payment")
	private String modeOfPayment;
	@Column(name="opening_Bal")
	private Long openingBal;
	@Column(name="opening_Bal_date")
	private LocalDate openingBalDate;
	@Column(name = "created_by",updatable=false)
    private String created_by;
    
    @Column(name = "created_date" ,updatable=false)
    private LocalDate created_date;
    
    @Column(name = "updated_by")
    private int updated_by;
    
    @Column(name="updated_date")
    private LocalDate updated_date;


    @Column(name="status")
	private String status;
    
    
}
