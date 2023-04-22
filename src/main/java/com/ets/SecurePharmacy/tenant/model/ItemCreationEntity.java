package com.ets.SecurePharmacy.tenant.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_ITEM_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class ItemCreationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(name = "item_name")
	private String itemName;
	@Column(name = "pack_name")
	private String packName;
	@Column(name = "qty_per_pack")
	private String qty_per_pack;
	@Column(name = "min_amt")
	private double min_amt;
	@Column(name = "max_amt")
	private double max_amt;
	@Column(name = "min_qty")
	private double min_qty;
	@Column(name = "max_qty")
	private double max_qty;
	@Column(name = "gst")
	private Integer gst;
	@Column(name = "rate_a")
	private String rateA;
	@Column(name = "rate_b")
	private String rateB;
	@Column(name = "rate_c")
	private String rateC;
	@Column(name = "rate_d")
	private String rateD;
	@Column(name="status")
	private String status;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	ManufacturerCreationEntity manufactureEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	GroupCreationEntity groupEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	StoreTypeCreationEntity storeTypeEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	SchedulerCreationEntity scheduleEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	CompostionCreationEntity compositionEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	DiscountSlabCreationEntity discSalbEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	HsnSacCreationEntity hsnsac;
	@Column(name = "created_by",updatable=false)
    private String  created_by;
	@Column(name = "created_date" ,updatable=false)
	private LocalDate created_date;
	@Column(name = "updated_by")
	private Integer updated_by;
	@Column(name="updated_date")
	private LocalDate updated_date;
	
	

}
