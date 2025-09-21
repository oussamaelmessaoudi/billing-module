package com.example.billingmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceExportDTO {
    private Long invoiceId;
    private LocalDate date;
    private String clientName;
    private String clientSiret;
    private List<InvoiceLineExportDTO> invoiceLines;
    private BigDecimal totalHT;
    private BigDecimal totalTTC;
    private BigDecimal totalTVA;
}
