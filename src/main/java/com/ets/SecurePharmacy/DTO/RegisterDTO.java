package com.ets.SecurePharmacy.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotBlank(message = "tenant name Required!!")
    private String tenantName;

    @NotBlank(message = "username Required!!")
    private String userName;

    private String userFirstName;

    private String userLastName;

    @NotBlank(message = "password Required!!")
    private String userPassword;

    @NotBlank(message = "role Required!!")
    private String role;

}
