package mena.gov.bf.proxies;

import mena.gov.bf.beans.EtapeExecution;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = ConstantsConfig.microserviceExecutionName, url = ConstantsConfig.microserviceExecutionUrl)
public interface EtapeExecutionRepository {
    @GetMapping("/etape-executions/{id}")
    EtapeExecution findEtapeExecutionById(@PathVariable("id") Long id);
}
