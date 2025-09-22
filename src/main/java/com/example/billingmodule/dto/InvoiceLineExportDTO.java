package com.example.billingmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceLineExportDTO {
    private Long invoiceLineId;
    private String description;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal tva;
    private BigDecimal lineTotalTVA;
    private BigDecimal lineTotalTTC;
}
