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
@Table(name = "TBL_BOY_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BoyCreationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "boy_name")
    private String boyName;
    @Column(name = "mobile_no", length = 15)
    private String mobileNo;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "salary", length = 15)
    private String salary;
    @Column(name="status")
    private String status;
    @Column(name = "created_by", updatable = false)
    private String created_by;
    @Column(name = "created_date", updatable = false)
    private LocalDate created_date;
    @Column(name = "updated_by")
    private int updated_by;
    @Column(name = "updated_date")
    private LocalDate updated_date;

}
