package com.bank.virement.dto;

public class BeneficiaireResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String rib;
    private String type;

    public BeneficiaireResponse() {
    }

    public BeneficiaireResponse(Long id, String nom, String prenom, String rib, String type) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

