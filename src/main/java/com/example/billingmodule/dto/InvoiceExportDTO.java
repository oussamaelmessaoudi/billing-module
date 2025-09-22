package com.example.billingmodule.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceExportDTO {
    private Long invoiceId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String clientName;
    private String clientSiret;
    private List<InvoiceLineExportDTO> invoiceLines;
    private BigDecimal totalHT;
    private BigDecimal totalTTC;
    private BigDecimal totalTVA;
}
