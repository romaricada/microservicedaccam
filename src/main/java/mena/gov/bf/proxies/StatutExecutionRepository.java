package mena.gov.bf.proxies;

import mena.gov.bf.beans.StatutExecution;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = ConstantsConfig.microserviceExecutionName, url = ConstantsConfig.microserviceExecutionUrl)
public interface StatutExecutionRepository {
    @GetMapping("/statut-executions/{id}")
    StatutExecution findStatutExecutionById(@PathVariable("id") Long id);
}
