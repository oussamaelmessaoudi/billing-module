package com.example.billingmodule.dto;

import com.example.billingmodule.validation.ValidSiret;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @ValidSiret
    private String siret;
}
