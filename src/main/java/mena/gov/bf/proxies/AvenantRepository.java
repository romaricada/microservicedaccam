package mena.gov.bf.proxies;

import mena.gov.bf.beans.Avenant;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = ConstantsConfig.microserviceExecutionName, url = ConstantsConfig.microserviceExecutionUrl)
public interface AvenantRepository {
    @GetMapping("/avenants/{id}")
    Avenant findAvenantById(@PathVariable("id") Long id);
}
