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
@Table(name="TBL_BEST_BEFORE")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BestBeforeEntity {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="best_before",length=250)
	private Double bestBefore;
	@Column(name="best_name")
	private String best_before_name;
	@Column(name="mode_status",length=10)
	private String status;
	

}
