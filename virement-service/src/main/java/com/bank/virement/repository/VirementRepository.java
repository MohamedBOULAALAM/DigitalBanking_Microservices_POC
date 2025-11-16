package com.bank.virement.repository;

import com.bank.virement.model.Virement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirementRepository extends JpaRepository<Virement, Long> {
    List<Virement> findByBeneficiaireId(Long beneficiaireId);
    List<Virement> findByRibSource(String ribSource);
}

