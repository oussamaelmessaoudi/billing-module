package com.example.billingmodule.repository;

import com.example.billingmodule.entity.Client;
import com.example.billingmodule.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    Optional<Invoice> findById(Long id);
    List<Invoice> findByClient(Client client);
    List<Invoice> findByDate(LocalDate date);
}
