package com.example.billingmodule.contoller;

import com.example.billingmodule.dto.ClientDTO;
import com.example.billingmodule.dto.ClientDetailDTO;
import com.example.billingmodule.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name= "Client", description = "API for managing clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @Operation(summary = "Create new Client")
    public ResponseEntity<String> createClient(@Valid @RequestBody ClientDTO clientDTO){
        clientService.createClient(clientDTO);
        return ResponseEntity.ok("Client created successfully");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID")
    public ResponseEntity<ClientDetailDTO> getClientById(@Valid @PathVariable Long id){
    ClientDetailDTO dto = clientService.getClientById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Get all clients")
    public ResponseEntity<List<ClientDetailDTO>> getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client by ID")
    public ResponseEntity<String> deleteClientById(@Valid @PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client has been deleted successfully !");
    }

}
