package mena.gov.bf.proxies;

import mena.gov.bf.beans.DecisionContentieux;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = ConstantsConfig.microserviceExecutionName, url = ConstantsConfig.microserviceExecutionUrl)
public interface DecisionContentieuxRepository {
    @GetMapping("/decision-contentieuxes/{id}")
    DecisionContentieux findDecisionContratById(@PathVariable("id") Long id);
}
