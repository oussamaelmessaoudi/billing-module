package com.example.billingmodule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    @NotNull
    private LocalDate date;
    @NotNull
    private Long clientId;
    @Size(min=1, message = "Invoice must contains at least one line")
    private List<InvoiceLineDTO> invoiceLines;

}
