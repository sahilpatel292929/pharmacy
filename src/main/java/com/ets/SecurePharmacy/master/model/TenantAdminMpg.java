package com.ets.SecurePharmacy.master.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantAdminMpg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_user_id")
    private User adminUser;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}
