package com.example.billingmodule.entity;

import com.example.billingmodule.validation.ValidTVA;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name ="invoice_lines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @NotBlank
    private String description;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false,name="unite_price")
    private BigDecimal unitPrice;
    @Column(nullable = false)
    @ValidTVA
    private BigDecimal tva;

    @Transient
    private BigDecimal lineTotalTVA;
    @Transient
    private BigDecimal lineTotalTTC;

}
