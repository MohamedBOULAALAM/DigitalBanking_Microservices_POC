package com.bank.virement.controller;

import com.bank.virement.dto.VirementDTO;
import com.bank.virement.service.VirementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/virements")
@Tag(name = "Virements", description = "API de gestion des virements bancaires")
public class VirementController {

    @Autowired
    private VirementService virementService;

    @PostMapping
    @Operation(summary = "Créer un virement", description = "Créer un nouveau virement bancaire")
    public ResponseEntity<VirementDTO> createVirement(@Valid @RequestBody VirementDTO dto) {
        VirementDTO created = virementService.createVirement(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un virement par ID", description = "Récupérer un virement par son identifiant")
    public ResponseEntity<VirementDTO> getVirementById(@PathVariable Long id) {
        VirementDTO dto = virementService.getVirementById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Obtenir tous les virements", description = "Récupérer la liste de tous les virements")
    public ResponseEntity<List<VirementDTO>> getAllVirements() {
        List<VirementDTO> virements = virementService.getAllVirements();
        return ResponseEntity.ok(virements);
    }

    @GetMapping("/beneficiaire/{beneficiaireId}")
    @Operation(summary = "Obtenir les virements par bénéficiaire", description = "Récupérer tous les virements d'un bénéficiaire")
    public ResponseEntity<List<VirementDTO>> getVirementsByBeneficiaireId(@PathVariable Long beneficiaireId) {
        List<VirementDTO> virements = virementService.getVirementsByBeneficiaireId(beneficiaireId);
        return ResponseEntity.ok(virements);
    }

    @GetMapping("/rib/{ribSource}")
    @Operation(summary = "Obtenir les virements par RIB source", description = "Récupérer tous les virements d'un RIB source")
    public ResponseEntity<List<VirementDTO>> getVirementsByRibSource(@PathVariable String ribSource) {
        List<VirementDTO> virements = virementService.getVirementsByRibSource(ribSource);
        return ResponseEntity.ok(virements);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un virement", description = "Supprimer un virement par son identifiant")
    public ResponseEntity<Void> deleteVirement(@PathVariable Long id) {
        virementService.deleteVirement(id);
        return ResponseEntity.noContent().build();
    }
}

