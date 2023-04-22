package com.ets.SecurePharmacy.tenant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_ORDER_DETAILS")
@Setter
@Getter
@Data
@NoArgsConstructor
@Transactional
public class OrderGenerationDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	

	@Column(name="order_qty")
	private Long orderQty;
	@Column(name="min_qty")
	private Long minQty;
	@Column(name="max_qty")
	private Long maxQty;
	@Column(name="stock")
	private String stock;
	@Column(name="y_sale")
	private Double ySale;
	@Column(name="w_sale")
	private Double wSale;
	@Column(name="m_sale")
	private Double mSale;
	@Column(name="pm_sale")
	private Double pmSale;
	
	@JsonBackReference
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(referencedColumnName="id")
	OrderGenerationEntity orderGenerationEntity;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	ItemCreationEntity items;
}
