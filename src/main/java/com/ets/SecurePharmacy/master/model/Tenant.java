package com.ets.SecurePharmacy.master.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private String username;
    private String password;
    @Column(name = "driver_class_name")
    private String driverClassName;

}