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
@Table(name="TBL_GST_TYPE")
@Getter
@Setter
@Data
@NoArgsConstructor
public class GSTTypeCreationEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="gst_type_name",length=250)
	private String gstTypeName;
	@Column(name="mode_status",length=10)
	private String status;

	
	

}
