package com.bank.beneficiaire.dto;

import com.bank.beneficiaire.model.TypeBeneficiaire;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BeneficiaireDTO {

    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "Le RIB est obligatoire")
    @Pattern(regexp = "^[0-9]{24}$", message = "Le RIB doit contenir 24 chiffres")
    private String rib;

    @NotNull(message = "Le type est obligatoire")
    private TypeBeneficiaire type;

    public BeneficiaireDTO() {
    }

    public BeneficiaireDTO(Long id, String nom, String prenom, String rib, TypeBeneficiaire type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.rib = rib;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public TypeBeneficiaire getType() {
        return type;
    }

    public void setType(TypeBeneficiaire type) {
        this.type = type;
    }
}

