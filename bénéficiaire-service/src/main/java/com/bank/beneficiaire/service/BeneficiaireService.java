package com.bank.beneficiaire.service;

import com.bank.beneficiaire.dto.BeneficiaireDTO;
import com.bank.beneficiaire.model.Beneficiaire;
import com.bank.beneficiaire.repository.BeneficiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BeneficiaireService {

    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    public BeneficiaireDTO createBeneficiaire(BeneficiaireDTO dto) {
        if (beneficiaireRepository.existsByRib(dto.getRib())) {
            throw new RuntimeException("Un bénéficiaire avec ce RIB existe déjà");
        }
        Beneficiaire beneficiaire = mapToEntity(dto);
        beneficiaire = beneficiaireRepository.save(beneficiaire);
        return mapToDTO(beneficiaire);
    }

    public BeneficiaireDTO getBeneficiaireById(Long id) {
        Beneficiaire beneficiaire = beneficiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bénéficiaire non trouvé avec l'id: " + id));
        return mapToDTO(beneficiaire);
    }

    public List<BeneficiaireDTO> getAllBeneficiaires() {
        return beneficiaireRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public BeneficiaireDTO updateBeneficiaire(Long id, BeneficiaireDTO dto) {
        Beneficiaire beneficiaire = beneficiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bénéficiaire non trouvé avec l'id: " + id));
        
        beneficiaire.setNom(dto.getNom());
        beneficiaire.setPrenom(dto.getPrenom());
        beneficiaire.setRib(dto.getRib());
        beneficiaire.setType(dto.getType());
        
        beneficiaire = beneficiaireRepository.save(beneficiaire);
        return mapToDTO(beneficiaire);
    }

    public void deleteBeneficiaire(Long id) {
        if (!beneficiaireRepository.existsById(id)) {
            throw new RuntimeException("Bénéficiaire non trouvé avec l'id: " + id);
        }
        beneficiaireRepository.deleteById(id);
    }

    public BeneficiaireDTO getBeneficiaireByRib(String rib) {
        Beneficiaire beneficiaire = beneficiaireRepository.findByRib(rib)
                .orElseThrow(() -> new RuntimeException("Bénéficiaire non trouvé avec le RIB: " + rib));
        return mapToDTO(beneficiaire);
    }

    private Beneficiaire mapToEntity(BeneficiaireDTO dto) {
        Beneficiaire beneficiaire = new Beneficiaire();
        beneficiaire.setNom(dto.getNom());
        beneficiaire.setPrenom(dto.getPrenom());
        beneficiaire.setRib(dto.getRib());
        beneficiaire.setType(dto.getType());
        return beneficiaire;
    }

    private BeneficiaireDTO mapToDTO(Beneficiaire beneficiaire) {
        return new BeneficiaireDTO(
                beneficiaire.getId(),
                beneficiaire.getNom(),
                beneficiaire.getPrenom(),
                beneficiaire.getRib(),
                beneficiaire.getType()
        );
    }
}

