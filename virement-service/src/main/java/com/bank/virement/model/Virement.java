package com.bank.virement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "virements")
public class Virement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "L'identifiant du bénéficiaire est obligatoire")
    @Column(nullable = false)
    private Long beneficiaireId;

    @NotBlank(message = "Le RIB source est obligatoire")
    @Column(nullable = false)
    private String ribSource;

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.01", message = "Le montant doit être supérieur à 0")
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal montant;

    @Column(length = 500)
    private String description;

    @NotNull(message = "La date est obligatoire")
    @Column(nullable = false)
    private LocalDateTime dateVirement;

    @NotNull(message = "Le type est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeVirement type;

    @PrePersist
    protected void onCreate() {
        if (dateVirement == null) {
            dateVirement = LocalDateTime.now();
        }
    }

    public Virement() {
    }

    public Virement(Long beneficiaireId, String ribSource, BigDecimal montant, String description, TypeVirement type) {
        this.beneficiaireId = beneficiaireId;
        this.ribSource = ribSource;
        this.montant = montant;
        this.description = description;
        this.type = type;
        this.dateVirement = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBeneficiaireId() {
        return beneficiaireId;
    }

    public void setBeneficiaireId(Long beneficiaireId) {
        this.beneficiaireId = beneficiaireId;
    }

    public String getRibSource() {
        return ribSource;
    }

    public void setRibSource(String ribSource) {
        this.ribSource = ribSource;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateVirement() {
        return dateVirement;
    }

    public void setDateVirement(LocalDateTime dateVirement) {
        this.dateVirement = dateVirement;
    }

    public TypeVirement getType() {
        return type;
    }

    public void setType(TypeVirement type) {
        this.type = type;
    }
}

