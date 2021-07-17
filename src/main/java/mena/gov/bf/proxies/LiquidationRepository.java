package mena.gov.bf.proxies;

import mena.gov.bf.beans.Liquidation;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = ConstantsConfig.microserviceExecutionName, url = ConstantsConfig.microserviceExecutionUrl)
public interface LiquidationRepository {
    @GetMapping("/liquidations/{id}")
    Liquidation findLiquidationById(@PathVariable("id") Long id);
}
