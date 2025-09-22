package com.example.billingmodule.service;

import com.example.billingmodule.dto.*;
import com.example.billingmodule.entity.Client;
import com.example.billingmodule.entity.Invoice;
import com.example.billingmodule.entity.InvoiceLine;
import com.example.billingmodule.exception.ClientNotFoundException;
import com.example.billingmodule.exception.InvoiceEmptyException;
import com.example.billingmodule.exception.InvoiceNotFoundException;
import com.example.billingmodule.exception.InvoiceTotalHTException;
import com.example.billingmodule.mapper.InvoiceLineMapper;
import com.example.billingmodule.mapper.InvoiceMapper;
import com.example.billingmodule.repository.ClientRepository;
import com.example.billingmodule.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final ClientRepository  clientRepository;
    private final InvoiceRepository invoiceRepository;

    public Invoice toModel(InvoiceDTO dto){
        Client client =  clientRepository.findById(dto.getClientId())
                .orElseThrow(()-> new ClientNotFoundException("Client not found"));

        return Invoice.builder()
                .date(dto.getDate())
                .client(client)
                .lines(dto.getInvoiceLines().stream().map(InvoiceLineMapper::toInvoice).toList())
                .build();
    }

    public List<InvoiceSummaryDTO> getAllInvoices(){
        return invoiceRepository.findAll().stream().map(InvoiceMapper::toDTO).toList();
    }

    public InvoiceSummaryDTO getInvoiceById(Long id){
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(()-> new InvoiceNotFoundException("Invoice not found !"));
        return InvoiceMapper.toDTO(invoice);
    }

    public List<InvoiceSummaryDTO> getInvoicesByClient(Long clientId){
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new ClientNotFoundException("Client not found !"));
        return invoiceRepository.findByClient(client).stream().map(InvoiceMapper::toDTO).toList();
    }

    public List<InvoiceSummaryDTO> getInvoicesByDate(LocalDate date){
        return invoiceRepository.findByDate(date).stream().map(InvoiceMapper::toDTO).toList();
    }

    public void createInvoice(InvoiceDTO dto){
        Client client = clientRepository.findById(dto.getClientId()).orElseThrow(()-> new ClientNotFoundException("Client not found !"));
        Invoice invoice = Invoice.builder()
                .date(dto.getDate())
                .client(client)
                .lines(dto.getInvoiceLines().stream().map(InvoiceLineMapper::toInvoice).toList())
                .build();
        calculateTotals(invoice);
        isValidInvoice(invoice);
        invoiceRepository.save(invoice);
    }

    public void updateInvoice(Long invoiceId, InvoiceDTO dto){
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()-> new InvoiceNotFoundException("Invoice not found !"));
        invoice.setDate(dto.getDate());
        invoice.setClient(clientRepository.findById(dto.getClientId()).orElseThrow(()-> new ClientNotFoundException("Client not found !")));
        isValidInvoice(invoice);
        invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long invoiceId){
        if(!invoiceRepository.existsById(invoiceId))
            throw new InvoiceNotFoundException("Invoice not found !");
        invoiceRepository.deleteById(invoiceId);
    }

    public void addLines(Long invoiceId,List<InvoiceLineDTO> dtos){
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()-> new InvoiceNotFoundException("Invoice not found !"));
        List<InvoiceLine> lines = dtos.stream().map(
                dto ->{
                    InvoiceLine line = InvoiceLineMapper.toInvoice(dto);
                    line.setInvoice(invoice);

                    BigDecimal ht = line.getUnitPrice().multiply(BigDecimal.valueOf(line.getQuantity()));
                    BigDecimal tva = ht.multiply(line.getTva()).divide(BigDecimal.valueOf(100));
                    BigDecimal ttc = ht.add(tva);

                    line.setLineTotalTTC(ttc);
                    line.setLineTotalTVA(tva);

                    return line;
                }).toList();
        if(invoice.getLines() == null) invoice.setLines(new ArrayList<>());
        invoice.getLines().addAll(lines);

        BigDecimal totalHT = invoice.getTotalHT() != null ? invoice.getTotalHT() : BigDecimal.ZERO;
        BigDecimal totalTVA = invoice.getTotalTVA() != null ? invoice.getTotalTVA() : BigDecimal.ZERO;

        for(InvoiceLine line : lines){
            BigDecimal ht = line.getUnitPrice().multiply(BigDecimal.valueOf(line.getQuantity()));
            BigDecimal tva = ht.multiply(line.getTva()).divide(BigDecimal.valueOf(100));

            totalHT = totalHT.add(ht);
            totalTVA = totalTVA.add(tva);
        }

        invoice.setTotalHT(totalHT);
        invoice.setTotalTVA(totalTVA);
        invoice.setTotalTTC(totalHT.add(totalTVA));
        isValidInvoice(invoice);
        invoiceRepository.save(invoice);
    }

    private void isValidInvoice(Invoice invoice){
        if(invoice.getLines().isEmpty()){
            throw new InvoiceEmptyException("An invoice must contains at least one line");
        }
        if(invoice.getTotalHT().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvoiceTotalHTException("Total HT must be greater than 0");
        }
    }
    public InvoiceExportDTO exportInvoices(Long invoiceId){
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()-> new InvoiceNotFoundException("Invoice not found !"));
        return InvoiceMapper.toExportDTO(invoice);
    }

    private void calculateTotals(Invoice invoice){
        BigDecimal totalHT = BigDecimal.ZERO;
        BigDecimal totalTVA = BigDecimal.ZERO;

        for(InvoiceLine line: invoice.getLines()){
            BigDecimal lineHT = line.getUnitPrice().multiply(BigDecimal.valueOf(line.getQuantity()));
            BigDecimal lineTVA = lineHT.multiply(line.getTva()).divide(BigDecimal.valueOf(100));

            totalHT = totalHT.add(lineHT);
            totalTVA = totalTVA.add(lineTVA);
            line.setLineTotalTVA(lineTVA);
            line.setLineTotalTTC(lineHT.add(lineTVA));
        }

        invoice.setTotalHT(totalHT);
        invoice.setTotalTVA(totalTVA);
        invoice.setTotalTTC(totalHT.add(totalTVA));
    }
}
