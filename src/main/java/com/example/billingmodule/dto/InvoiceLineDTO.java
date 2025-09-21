package com.example.billingmodule.dto;

import com.example.billingmodule.validation.ValidTVA;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceLineDTO {
    @NotBlank
    private String description;

    @Min(1)
    private int quantity;

    @DecimalMin("0.0")
    private BigDecimal unitPrice;

    @ValidTVA
    private BigDecimal tva;
}
