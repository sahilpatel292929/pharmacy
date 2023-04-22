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
@Table(name="TBL_ACCOUNT_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class AccountCreationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="account_name")
    private String actName;
    
    @Column(name="account_type",nullable=false)
    private String actType;
    
    @Column(name="account_group", nullable=false, length=200)
    private String actGroup;
    @Column(name="opening_balance")
    private String openingBal;
    @Column(name="opening_bal_date")
    private LocalDate openingBalDate;
    @Column(name = "created_by",updatable=false)
    private Integer created_by;
    
    @Column(name="status")
	private String status;
    
    @Column(name = "created_date" ,updatable=false)
    private LocalDate created_date;
    
    @Column(name = "updated_by")
    private Integer updated_by;
    
    @Column(name="updated_date")
    private LocalDate updated_date;

	

}