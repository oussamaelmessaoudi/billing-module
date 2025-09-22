package com.example.billingmodule.contoller;

import com.example.billingmodule.dto.InvoiceDTO;
import com.example.billingmodule.dto.InvoiceExportDTO;
import com.example.billingmodule.dto.InvoiceLineDTO;
import com.example.billingmodule.dto.InvoiceSummaryDTO;
import com.example.billingmodule.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@Tag(name="invoice", description = "API for managing invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping
    @Operation(summary = "Get all invoices")
    public ResponseEntity<List<InvoiceSummaryDTO>> getAllInvoices(){
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/client/{id}")
    @Operation(summary = "Get a client's invoices by client ID")
    public ResponseEntity<List<InvoiceSummaryDTO>> getClientInvoices(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok(invoiceService.getInvoicesByClient(id));
    }

    @GetMapping("/{date}")
    @Operation(summary = "Get invoices by date")
    public ResponseEntity<List<InvoiceSummaryDTO>> getClientInvoicesByDate(@RequestParam LocalDate date){
        return ResponseEntity.ok(invoiceService.getInvoicesByDate(date));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create new invoice")
    public ResponseEntity<String> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO){
        invoiceService.createInvoice(invoiceDTO);
        return ResponseEntity.ok("Invoice created successfully !");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update an invoice by id")
    public ResponseEntity<String> updateInvoice(@Valid @PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO){
        invoiceService.updateInvoice(id, invoiceDTO);
        return ResponseEntity.ok("Invoice updated successfully !");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an invoice by id")
    public ResponseEntity<String> deleteInvoice(@Valid @PathVariable("id") Long id){
        invoiceService.deleteInvoice(id);
        return ResponseEntity.ok("Invoice deleted successfully !");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/invoiceLine/{id}")
    @Operation(summary = "Add lines into an invoice using it's ID")
    public ResponseEntity<String> addLines(@Valid @PathVariable("id") Long id, @RequestBody List<InvoiceLineDTO> dtos){
        invoiceService.addLines(id, dtos);
        return ResponseEntity.ok("Lines added successfully !");
    }

    @GetMapping("/{id}/export")
    @Operation(summary = "Export invoice with lines in JSON format")
    public ResponseEntity<InvoiceExportDTO> exportInvoice(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok(invoiceService.exportInvoices(id));
    }
}
