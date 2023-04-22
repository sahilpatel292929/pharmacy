package com.ets.SecurePharmacy.tenant.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name="TBL_OPENING_STOCK_ENTRY")
@Getter
@Setter
@Data
@NoArgsConstructor
@Transactional
public class OpenStockEntryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name="order_date")
	private LocalDate orderDate;
	@Column(name="srt")
	private Double srt;
	@Column(name="total_items")
	private Integer totalItems;
	@Column(name="mrp_amount")
	private Double mrpAmount;
	@Column(name="purchase_val")
	private Double purchaseVal;
	@Column(name="sales_val")
	private Double salesVal;
	@Column(name="del_status")
	private String delStatus;
	@Column(name="status")
	private String status;

	@JsonManagedReference
	@OneToMany(mappedBy = "openStockEntry", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    List<OpenStockEntryDetailsEntity> openStockDetails;

	
	
	
}
