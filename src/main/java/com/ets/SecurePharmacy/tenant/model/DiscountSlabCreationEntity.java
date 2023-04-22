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
@Table(name="TBL_DISCOUNT_SLAB_CREATION")
@Getter
@Setter
@NoArgsConstructor
@Data
public class DiscountSlabCreationEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="dicount_slab_name",length=250)
	private String discountSlabName;
	@Column(name="discount")
	private String discount;
	@Column(name="from_amt",length=10)
	private Integer from_amt;
	@Column(name="to_amt",length=10)
	private Integer to_amt;
	
	@Column(name="status",length=10)
	private String status;
	
	
}
