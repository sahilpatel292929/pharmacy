package com.ets.SecurePharmacy.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String tenantName;

}
