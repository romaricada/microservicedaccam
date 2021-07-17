package mena.gov.bf.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import mena.gov.bf.beans.PpmActivite;
import mena.gov.bf.utils.ConstantsConfig;

@FeignClient(name = ConstantsConfig.microserviceppmName, url = ConstantsConfig.microserviceppmUrl)
public interface PpmActiviteRepository {
    @GetMapping("/ppm-activites/{id}")
    PpmActivite findPpmActiviteById(@PathVariable("id") Long id);
}
