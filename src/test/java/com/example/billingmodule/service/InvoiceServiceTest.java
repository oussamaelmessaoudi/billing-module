package com.example.billingmodule.service;

import com.example.billingmodule.dto.InvoiceDTO;
import com.example.billingmodule.dto.InvoiceLineDTO;
import com.example.billingmodule.entity.Client;
import com.example.billingmodule.entity.Invoice;
import com.example.billingmodule.repository.ClientRepository;
import com.example.billingmodule.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Test
    void shouldCreateInvoiceSuccessfully() {
        // Arrange
        Long clientId = 1L;
        Client client = Client.builder().id(clientId).name("Soulaimane").siret("14725836912345").build();

        InvoiceLineDTO lineDTO = InvoiceLineDTO.builder()
                .description("USB-C cable")
                .unitPrice(BigDecimal.valueOf(8.99))
                .quantity(3)
                .tva(BigDecimal.valueOf(20))
                .build();

        InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                .clientId(clientId)
                .date(LocalDate.of(2025, 9, 22))
                .invoiceLines(List.of(lineDTO))
                .build();

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
//        when(invoiceRepository.save(any(Invoice.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        invoiceService.createInvoice(invoiceDTO);

        // Assert
        ArgumentCaptor<Invoice> captor = ArgumentCaptor.forClass(Invoice.class);
        verify(invoiceRepository).save(captor.capture());

        Invoice savedInvoice = captor.getValue();
        assertEquals(client, savedInvoice.getClient());
        assertEquals(BigDecimal.valueOf(26.97), savedInvoice.getTotalHT());
        assertEquals(BigDecimal.valueOf(5.394), savedInvoice.getTotalTVA());
        assertEquals(BigDecimal.valueOf(32.364), savedInvoice.getTotalTTC());
    }
}
