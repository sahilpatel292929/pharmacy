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
@Table(name="TBL_COUNTER_SALE")
@Getter
@Setter
@Data
@NoArgsConstructor
@Transactional
public class CounterSaleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "entry_date")
	private LocalDate entryDate;
	@Column(name = "total_items")
	private int totalItems;
	@Column(name="total_value")
	private Double totalVal;
	@Column(name = "status")
	private String status;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "counterSaleEntry", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    List<CounterSaleDetailEntity> counteSaleDetails;

}
