package mena.gov.bf.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import mena.gov.bf.beans.PPM;
import mena.gov.bf.utils.ConstantsConfig;

@FeignClient(name = ConstantsConfig.microserviceppmName, url = ConstantsConfig.microserviceppmUrl)
public interface PpmRepository {
    @GetMapping("/ppms/{id}")
    PPM finPpmById(@PathVariable("id") Long id);
}
