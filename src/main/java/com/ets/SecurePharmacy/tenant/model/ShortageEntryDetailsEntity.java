package com.ets.SecurePharmacy.tenant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="TBL_SHORTAGE_ENTRY_DETAIL")
@Getter
@Setter
@Data
@NoArgsConstructor
@Transactional
public class ShortageEntryDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	
	@Column(name="qty")
    private Double qty;
	
	@JsonBackReference
	@ManyToOne(optional=false)
	@JoinColumn(referencedColumnName = "ID",nullable=false)
	ShortageEntryEntity shortageEntryEntity;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	ItemCreationEntity items;
	
	
	

}
