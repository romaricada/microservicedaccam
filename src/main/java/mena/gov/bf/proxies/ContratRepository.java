package mena.gov.bf.proxies;

import mena.gov.bf.beans.Contrat;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ConstantsConfig.microserviceExecutionName, url = "localhost:8804/api")
public interface ContratRepository {
    @GetMapping("/contrats/{id}")
    Contrat findContratById(@PathVariable("id") Long id);

    @GetMapping("/contrats/contrat-by-candidatLot")
    Contrat findContratByCandidatLot(@RequestParam(name = "candidatLotId") Long candidatLotId);
}
