package com.ets.SecurePharmacy.tenant.model;

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
@Table(name="TBL_COMPOSITION_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class CompostionCreationEntity {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="c_name",length=250)
	private String compName;
	@Column(name="information")
	private String information;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "ID")
	SchedulerCreationEntity schedulerCreationEntity;
	
	@Column(name="mode_status",length=10)
	private String status;
	
	
}
