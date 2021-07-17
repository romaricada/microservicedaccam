package mena.gov.bf.proxies;

import mena.gov.bf.beans.Timbre;
import org.springframework.web.bind.annotation.GetMapping;

public interface TimbreRepository {

    @GetMapping("/timbres/timbres")
    public Timbre getTimbres();
}
