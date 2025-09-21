package com.example.billingmodule.service;

import com.example.billingmodule.dto.InvoiceDTO;
import com.example.billingmodule.entity.Client;
import com.example.billingmodule.entity.Invoice;
import com.example.billingmodule.exception.ClientNotFoundException;
import com.example.billingmodule.mapper.InvoiceLineMapper;
import com.example.billingmodule.mapper.InvoiceMapper;
import com.example.billingmodule.repository.ClientRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final ClientRepository  clientRepository;

    public Invoice toModel(InvoiceDTO dto){
        Client client =  clientRepository.findById(dto.getClientId())
                .orElseThrow(()-> new ClientNotFoundException("Client not found"));

        return Invoice.builder()
                .date(dto.getDate())
                .client(client)
                .lines(dto.getInvoiceLines().stream().map(InvoiceLineMapper::toInvoice).toList())
                .build();
    }

}
