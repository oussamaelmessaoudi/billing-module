package com.example.billingmodule.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Builder
@AllArgsConstructor
public class InvoiceSummaryDTO {
    private Long invoiceId;
    private LocalDate date;
    private BigDecimal totalHT;
    private BigDecimal totalTVA;
    private BigDecimal totalTTC;
}
