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
@Table(name="TBL_SCHEDULER_CREATION")
@Getter
@Setter
@Data
@NoArgsConstructor
public class SchedulerCreationEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="scheduler_name",length=250)
	private String schedulerName;
	@Column(name="warning_msg")
	private String waringMsg;
	@Column(name="warning")
	private String warning;
	@Column(name="mode_status",length=10)
	private String status;

	
}
