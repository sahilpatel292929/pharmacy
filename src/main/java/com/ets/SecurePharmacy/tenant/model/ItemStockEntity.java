package com.ets.SecurePharmacy.tenant.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name="TBL_ITEM_STOCK_ENTRY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemStockEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="add_stock")
	private Long addStock;
	@Column(name="less_stock")
	private Double lessStock;
	@Column(name="available_stock")
	private Long availableStock;
	
	
	@UpdateTimestamp
	private LocalDateTime lastUpdateTime;
	@CreatedDate
	private LocalDateTime createdTime;
	
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	ItemCreationEntity itemEntity;
	
}
