package com.example.billingmodule.mapper;

import com.example.billingmodule.dto.*;
import com.example.billingmodule.entity.*;

public class InvoiceLineMapper {

    public static InvoiceLine toInvoice(InvoiceLineDTO dto){
        return InvoiceLine.builder()
                .description(dto.getDescription())
                .tva(dto.getTva())
                .unitPrice(dto.getUnitPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}
