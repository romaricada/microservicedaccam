package mena.gov.bf.proxies;

import mena.gov.bf.beans.Serveur;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = ConstantsConfig.microserviceGEDName, url = ConstantsConfig.microserviceGEDUrl)
public interface ServeurRepository {

    @GetMapping("/serveurs/serveur-actif")
    Serveur getServeurActif();
}
