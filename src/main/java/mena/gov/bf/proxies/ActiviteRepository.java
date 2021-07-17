package mena.gov.bf.proxies;

import mena.gov.bf.beans.Activite;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@FeignClient(name = ConstantsConfig.microserviceppmName, url = ConstantsConfig.microserviceppmUrl)
public interface ActiviteRepository {

    @RequestMapping(value = "/activites", method = RequestMethod.GET)
    List<Activite> listActivites();

    @GetMapping("/activites/{id}")
    Activite findActiviteById(@PathVariable("id") Long id);

    @GetMapping("/activites/all-by-annee")
    List<Activite> findAllActiviteByAnnee (@RequestParam(name = "idAnnee") Long idAnnee);
}
