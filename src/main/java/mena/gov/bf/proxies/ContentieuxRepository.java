package mena.gov.bf.proxies;

import mena.gov.bf.beans.Contentieux;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = ConstantsConfig.microserviceExecutionName, url = ConstantsConfig.microserviceExecutionUrl)
public interface ContentieuxRepository {
    @GetMapping("/contentieuxes/{id}")
    Contentieux findContentieuxById(@PathVariable("id") Long id);

    @GetMapping("/contentieuxes/contentieux-by-contrat")
    List<Contentieux> getAllContentieuxesByContrat(@RequestParam(name = "contratId") Long contratId);
}
