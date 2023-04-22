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
@Table(name="TBL_PURCHASE_ENTRY_SETTINGS")
@Getter
@Setter
@Data
@NoArgsConstructor
public class PurchaseEntrySettingEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="setting_name")
    private String settingName;
    
    @Column(name = "created_date" ,updatable=false)
    private LocalDate created_date;
    
    @Column(name = "updated_by")
    private int updated_by;
    
    @Column(name="updated_date")
    private LocalDate updated_date;
    @Column(name="status")
    private String status;

    
    
}
