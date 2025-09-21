package com.example.billingmodule.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDetailDTO {
    private Long clientId;
    private String name;
    private String email;
    private String siret;
    private LocalDate createdAt;
    private List<InvoiceSummaryDTO> invoices;
}
