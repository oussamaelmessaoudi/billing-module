package com.example.billingmodule.dto;

import java.math.BigDecimal;

public class InvoiceLineExportDTO {
    private Long invoiceLineId;
    private String description;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal tva;
    private BigDecimal lineTotalTVA;
    private BigDecimal lineTotalTTC;
}
