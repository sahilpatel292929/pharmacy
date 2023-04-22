package com.ets.SecurePharmacy.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantCreationDTO {

    @NotBlank
    private String newTenantName;

    private String ExistingTenantName;

}
