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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_PUCHASE_ORDER")
@Setter
@Getter
@Data
@NoArgsConstructor
@Transactional
public class PurchaseOrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name="supplier_name")
	private String supplierName;
	@Column(name="order_date")
	private LocalDate orderDate;
	@Column(name="total_count")
	private Long totalCount;
	@Column(name="order_qty")
	private Long orderQty;
	@Column(name="status")
	private String status;

	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	SupplierCreationEntity supplierDetails;
	
	public Long getSupplierDetailsId() {
	    return this.supplierDetails.getId();
	  }
	
	@JsonManagedReference
	@OneToMany(
		    mappedBy = "purchaseOrderEntity", 
		    cascade = CascadeType.ALL,
		    fetch=FetchType.EAGER)
    List<PurchaseOrderDetailsEntity> purchaseOrderDetails;
}
