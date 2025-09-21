package com.example.billingmodule.entity;

import com.example.billingmodule.validation.ValidSiret;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name ="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "name cannot be blank")
    @Size(min =2,max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]{2,50}$")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "email cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @JsonIgnore
    private String email;

    @Column(nullable = false,unique = true)
    @ValidSiret
    private String siret;

    @Column(nullable = false,name = "created_at")
    private LocalDate createdAt;

    @Setter
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "client")
    private List<Invoice> invoices;

    @PrePersist
    public void assignCreatedAt(){
        this.createdAt = LocalDate.now();
    }

}
