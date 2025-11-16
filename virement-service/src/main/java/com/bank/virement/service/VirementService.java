package com.bank.virement.service;

import com.bank.virement.client.BeneficiaireClient;
import com.bank.virement.dto.VirementDTO;
import com.bank.virement.model.Virement;
import com.bank.virement.repository.VirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VirementService {

    @Autowired
    private VirementRepository virementRepository;

    @Autowired
    private BeneficiaireClient beneficiaireClient;

    public VirementDTO createVirement(VirementDTO dto) {
        // Vérifier que le bénéficiaire existe
        try {
            beneficiaireClient.getBeneficiaireById(dto.getBeneficiaireId());
        } catch (Exception e) {
            throw new RuntimeException("Bénéficiaire non trouvé avec l'id: " + dto.getBeneficiaireId());
        }

        Virement virement = mapToEntity(dto);
        virement = virementRepository.save(virement);
        return mapToDTO(virement);
    }

    public VirementDTO getVirementById(Long id) {
        Virement virement = virementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Virement non trouvé avec l'id: " + id));
        return mapToDTO(virement);
    }

    public List<VirementDTO> getAllVirements() {
        return virementRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<VirementDTO> getVirementsByBeneficiaireId(Long beneficiaireId) {
        return virementRepository.findByBeneficiaireId(beneficiaireId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<VirementDTO> getVirementsByRibSource(String ribSource) {
        return virementRepository.findByRibSource(ribSource).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteVirement(Long id) {
        if (!virementRepository.existsById(id)) {
            throw new RuntimeException("Virement non trouvé avec l'id: " + id);
        }
        virementRepository.deleteById(id);
    }

    private Virement mapToEntity(VirementDTO dto) {
        Virement virement = new Virement();
        virement.setBeneficiaireId(dto.getBeneficiaireId());
        virement.setRibSource(dto.getRibSource());
        virement.setMontant(dto.getMontant());
        virement.setDescription(dto.getDescription());
        virement.setType(dto.getType());
        if (dto.getDateVirement() != null) {
            virement.setDateVirement(dto.getDateVirement());
        }
        return virement;
    }

    private VirementDTO mapToDTO(Virement virement) {
        return new VirementDTO(
                virement.getId(),
                virement.getBeneficiaireId(),
                virement.getRibSource(),
                virement.getMontant(),
                virement.getDescription(),
                virement.getDateVirement(),
                virement.getType()
        );
    }
}

