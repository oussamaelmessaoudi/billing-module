package com.example.billingmodule.mapper;

import com.example.billingmodule.dto.*;
import com.example.billingmodule.entity.*;

import java.math.BigDecimal;

public class InvoiceLineMapper {

    public static InvoiceLine toInvoice(InvoiceLineDTO dto){
        BigDecimal totalLineHT = dto.getUnitPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));
        BigDecimal totalLineTVA = totalLineHT.multiply(dto.getTva()).divide(BigDecimal.valueOf(100));
        BigDecimal totalLineTCC = totalLineHT.add(totalLineTVA);

        return InvoiceLine.builder()
                .description(dto.getDescription())
                .tva(dto.getTva())
                .unitPrice(dto.getUnitPrice())
                .quantity(dto.getQuantity())
                .lineTotalTVA(totalLineTVA)
                .lineTotalTTC(totalLineTCC)
                .build();
    }

    public static InvoiceLineExportDTO toLineExportDTO(InvoiceLine invoiceLine){
        BigDecimal totalLineHT = invoiceLine.getUnitPrice().multiply(BigDecimal.valueOf(invoiceLine.getQuantity()));
        BigDecimal totalLineTVA = totalLineHT.multiply(invoiceLine.getTva()).divide(BigDecimal.valueOf(100));
        BigDecimal totalLineTCC = totalLineHT.add(totalLineTVA);
        return InvoiceLineExportDTO.builder()
                .invoiceLineId(invoiceLine.getId())
                .description(invoiceLine.getDescription())
                .quantity(invoiceLine.getQuantity())
                .unitPrice(invoiceLine.getUnitPrice())
                .tva(invoiceLine.getTva())
                .lineTotalTTC(totalLineTCC)
                .lineTotalTVA(totalLineTVA)
                .build();
    }
}
