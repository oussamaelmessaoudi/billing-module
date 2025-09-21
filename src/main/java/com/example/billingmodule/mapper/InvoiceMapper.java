package com.example.billingmodule.mapper;

import com.example.billingmodule.dto.InvoiceDTO;
import com.example.billingmodule.dto.InvoiceSummaryDTO;
import com.example.billingmodule.entity.Client;
import com.example.billingmodule.entity.Invoice;
import com.example.billingmodule.exception.ClientNotFoundException;
import com.example.billingmodule.repository.ClientRepository;
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

}
