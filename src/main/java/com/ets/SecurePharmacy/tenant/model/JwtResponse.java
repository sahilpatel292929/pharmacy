package com.ets.SecurePharmacy.tenant.model;

import com.ets.SecurePharmacy.DTO.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class JwtResponse {

    private UserResponseDTO user;
    private String jwtToken;

}