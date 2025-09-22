package com.example.billingmodule.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="invoices")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<InvoiceLine> lines = new ArrayList<>();

    @Column(nullable = false, name = "total_ht")
    private BigDecimal totalHT;
    @Column(nullable = false, name = "total_tva")
    private BigDecimal totalTVA;
    @Column(nullable = false, name = "total_ttc")
    private BigDecimal totalTTC;

}
