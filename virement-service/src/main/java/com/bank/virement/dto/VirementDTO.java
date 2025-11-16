package com.bank.virement.dto;

import com.bank.virement.model.TypeVirement;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VirementDTO {

    private Long id;

    @NotNull(message = "L'identifiant du bénéficiaire est obligatoire")
    private Long beneficiaireId;

    @NotBlank(message = "Le RIB source est obligatoire")
    private String ribSource;

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.01", message = "Le montant doit être supérieur à 0")
    private BigDecimal montant;

    private String description;

    private LocalDateTime dateVirement;

    @NotNull(message = "Le type est obligatoire")
    private TypeVirement type;

    public VirementDTO() {
    }

    public VirementDTO(Long id, Long beneficiaireId, String ribSource, BigDecimal montant, 
                      String description, LocalDateTime dateVirement, TypeVirement type) {
        this.id = id;
        this.beneficiaireId = beneficiaireId;
        this.ribSource = ribSource;
        this.montant = montant;
        this.description = description;
        this.dateVirement = dateVirement;
        this.type = type;
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

