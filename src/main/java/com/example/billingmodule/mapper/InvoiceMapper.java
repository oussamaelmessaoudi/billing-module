package com.example.billingmodule.mapper;

import com.example.billingmodule.dto.InvoiceExportDTO;
import com.example.billingmodule.dto.InvoiceSummaryDTO;
import com.example.billingmodule.entity.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {

    public static InvoiceSummaryDTO toDTO(Invoice invoice){
        return InvoiceSummaryDTO.builder()
                .invoiceId(invoice.getId())
                .date(invoice.getDate())
                .totalHT(invoice.getTotalHT())
                .totalTVA(invoice.getTotalTVA())
                .totalTTC(invoice.getTotalTTC())
                .build();
    }

    public static InvoiceExportDTO toExportDTO(Invoice invoice){
        return InvoiceExportDTO.builder()
                .invoiceId(invoice.getId())
                .date(invoice.getDate())
                .clientName(invoice.getClient().getName())
                .clientSiret(invoice.getClient().getSiret())
                .invoiceLines(invoice.getLines().stream().map(InvoiceLineMapper::toLineExportDTO).toList())
                .totalHT(invoice.getTotalHT())
                .totalTTC(invoice.getTotalTTC())
                .totalTVA(invoice.getTotalTVA())
                .build();
    }

}
