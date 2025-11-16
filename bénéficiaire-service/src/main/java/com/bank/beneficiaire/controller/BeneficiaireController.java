package com.bank.beneficiaire.controller;

import com.bank.beneficiaire.dto.BeneficiaireDTO;
import com.bank.beneficiaire.service.BeneficiaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiaires")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
@Tag(name = "Bénéficiaires", description = "API de gestion des bénéficiaires")
public class BeneficiaireController {

    @Autowired
    private BeneficiaireService beneficiaireService;

    @PostMapping
    @Operation(summary = "Créer un bénéficiaire", description = "Créer un nouveau bénéficiaire")
    public ResponseEntity<BeneficiaireDTO> createBeneficiaire(@Valid @RequestBody BeneficiaireDTO dto) {
        BeneficiaireDTO created = beneficiaireService.createBeneficiaire(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un bénéficiaire par ID", description = "Récupérer un bénéficiaire par son identifiant")
    public ResponseEntity<BeneficiaireDTO> getBeneficiaireById(@PathVariable Long id) {
        BeneficiaireDTO dto = beneficiaireService.getBeneficiaireById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Obtenir tous les bénéficiaires", description = "Récupérer la liste de tous les bénéficiaires")
    public ResponseEntity<List<BeneficiaireDTO>> getAllBeneficiaires() {
        List<BeneficiaireDTO> beneficiaires = beneficiaireService.getAllBeneficiaires();
        return ResponseEntity.ok(beneficiaires);
    }

    @GetMapping("/rib/{rib}")
    @Operation(summary = "Obtenir un bénéficiaire par RIB", description = "Récupérer un bénéficiaire par son RIB")
    public ResponseEntity<BeneficiaireDTO> getBeneficiaireByRib(@PathVariable String rib) {
        BeneficiaireDTO dto = beneficiaireService.getBeneficiaireByRib(rib);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un bénéficiaire", description = "Mettre à jour les informations d'un bénéficiaire")
    public ResponseEntity<BeneficiaireDTO> updateBeneficiaire(
            @PathVariable Long id,
            @Valid @RequestBody BeneficiaireDTO dto) {
        BeneficiaireDTO updated = beneficiaireService.updateBeneficiaire(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un bénéficiaire", description = "Supprimer un bénéficiaire par son identifiant")
    public ResponseEntity<Void> deleteBeneficiaire(@PathVariable Long id) {
        beneficiaireService.deleteBeneficiaire(id);
        return ResponseEntity.noContent().build();
    }
}

