package mena.gov.bf.downloadFile;

import mena.gov.bf.domain.enumeration.TypeDossier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DownloadFileResource {
    private static final Logger log = LoggerFactory.getLogger( DownloadFileResource.class );
    private final DownLoadFileService downLoadFileService;

    public DownloadFileResource(DownLoadFileService downLoadFileService) {
        this.downLoadFileService = downLoadFileService;
    }

    @GetMapping("/downloads/download-file")
    ResponseEntity<Void> dowloadFile(@RequestParam(name = "id") Long id, @RequestParam(name = "typeDossier") TypeDossier typeDossier) {
        downLoadFileService.exporteFile( id, typeDossier );
        return ResponseEntity.noContent().build();
    }
}
