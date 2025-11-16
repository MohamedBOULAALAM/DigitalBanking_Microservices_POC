package com.bank.virement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "beneficiaire-service")
public interface BeneficiaireClient {

    @GetMapping("/beneficiaires/{id}")
    Object getBeneficiaireById(@PathVariable Long id);
}

