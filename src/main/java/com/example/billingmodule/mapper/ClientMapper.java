package com.example.billingmodule.mapper;

import com.example.billingmodule.dto.ClientDTO;
import com.example.billingmodule.dto.ClientDetailDTO;
import com.example.billingmodule.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    public static ClientDetailDTO toDTO(Client client) {
        return ClientDetailDTO.builder()
                .clientId(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .siret(client.getSiret())
                .invoices(client.getInvoices().stream()
                        .map(InvoiceMapper::toDTO)
                        .toList())
                .createdAt(client.getCreatedAt())
                .build();
    }

    public static Client toModel(ClientDTO dto) {
        return Client.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .siret(dto.getSiret())
                .createdAt(LocalDate.now())
                .build();
    }
}
