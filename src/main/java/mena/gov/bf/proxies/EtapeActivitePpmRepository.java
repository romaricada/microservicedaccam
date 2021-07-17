package mena.gov.bf.proxies;

import mena.gov.bf.beans.EtapeActivitePpm;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = ConstantsConfig.microserviceppmName, url = ConstantsConfig.microserviceppmUrl)
public interface EtapeActivitePpmRepository {
    @GetMapping("/etape-activite-ppms/{id}")
    EtapeActivitePpm findEtapeActivitePpmById(@PathVariable("id") Long id);
}
