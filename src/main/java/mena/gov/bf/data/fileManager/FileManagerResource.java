package mena.gov.bf.data.fileManager;

import mena.gov.bf.domain.enumeration.TypeDossier;
import mena.gov.bf.model.DataFile;
import mena.gov.bf.model.ExecutionFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mena.gov.bf.proxies.ServeurRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileManagerResource {

    private static final Logger log = LoggerFactory.getLogger(FileManagerResource.class);
    private final ServeurRepository serveurRepository;
    private final FileManagerService fileManagerService;

    public FileManagerResource(ServeurRepository serveurRepository, FileManagerService fileManagerService) {
        this.serveurRepository = serveurRepository;
        this.fileManagerService = fileManagerService;
    }

    // @PostMapping("/files/file")
    // public void uploadFile(HttpServletResponse response, MultipartFile file, @RequestParam(name = "id") Long id)
    //         throws IOException {
    //     if (getServeurType().equals("SFTP"))
    //         sftpManagerService.uploadFTP(file, id);
    //     else if (getServeurType().equals("FTP"))
    //         fileManagerService.uploadFTP(file, id);
    // }

    // @DeleteMapping("/files/file")
    // public void deleteFTPFile(HttpServletResponse response, @RequestParam(name = "id") Long id) {
    //     sftpManagerService.deleteFTPFile(id, true);
    // }

    // @GetMapping("/files/testFile")
    // public ResponseEntity<ResponseMessage> uploadMultiFile(@RequestParam(name = "id") Long id) {
    //     String message = "";
    //     boolean success = sftpManagerService.sftpConnect();
    //     DataFile dataFile = sftpManagerService.getDocumentDataFile(id, success);
    //     message = "[Data file name: " + dataFile.getFileName() + ", Data file blob: " + dataFile.getFile() + "]";
    //     return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

    // }

    // @GetMapping("/files/file")
    // public ResponseEntity<InputStreamResource> getFTPFile(HttpServletResponse response,
    //         @RequestParam(name = "id") Long id) throws IOException {

    //     File file = fileManagerService.downloadFTPFile(response, id);
    //     try {
    //         InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    //         // MediaType mediaType =
    //         // MediaTypeUtils.getMediaTypeForFileName(this.servletContext, filename);
    //         ResponseEntity<InputStreamResource> responseEntity = ResponseEntity.ok()
    //                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
    //                 .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length()).body(resource);

    //         return responseEntity;
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         return null;

    //     }

    // }

    // /**
    //  * API de chargement de plusieurs fichiers
    //  *
    //  * @param files
    //  * @param id
    //  * @return
    //  */
    // @PostMapping("/files/upload-files")
    // public ResponseEntity<ResponseMessage> uploadMultiFile(@RequestParam(name = "files") List<MultipartFile> files,
    //         @RequestParam(name = "id") Long id) {
    //     String message = "";
    //     try {
    //         fileManagerService.save(files, id);
    //         message = "Uploaded the file successfully: ";
    //         return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    //     } catch (Exception e) {
    //         // message = "Could not upload the file: " + file.getOriginalFilename() + "!";
    //         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    //     }
    // }

    // @GetMapping("/files/file-liste-by-entrepot")
    // public ResponseEntity<List<DataFile>> getListFilesByEntrepot(@RequestParam(name = "id") Long id) {
    //     List<DataFile> datas = new ArrayList<>();
    //     if (serveurService.getFirstServeur().isPresent()) {
    //         if (serveurService.getFirstServeur().get().getTypeServeur().name().equals("SFTP"))
    //             datas = sftpManagerService.findAllFilesByEntrepot(id);
    //         else if (serveurService.getFirstServeur().get().getTypeServeur().name().equals("FTP"))
    //             datas = fileManagerService.findAllFilesByEntrepot(id);
    //     }

    //     return ResponseEntity.status(HttpStatus.OK).body(datas);
    // }

    // @GetMapping("/files/file-liste")
    // public ResponseEntity<List<DataFile>> getListFiles() {
    //     List<DataFile> datas = new ArrayList<>();
    //     if (serveurService.getFirstServeur().isPresent()) {
    //         if (serveurService.getFirstServeur().get().getTypeServeur().name().equals("SFTP"))
    //             datas = sftpManagerService.findAllFiles();
    //         else if (serveurService.getFirstServeur().get().getTypeServeur().name().equals("FTP"))
    //             datas = fileManagerService.findAllFiles();
    //     }

    //     return ResponseEntity.status(HttpStatus.OK).body(datas);
    // }

    // @GetMapping("/files/{filename:.+}")
    // @ResponseBody
    // public ResponseEntity<Resource> getFile(@PathVariable String filename, @RequestParam(name = "id") Long id) {
    //     Resource file = fileManagerService.load(filename, id);
    //     return ResponseEntity.ok()
    //             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
    //             .body(file);
    // }

    private String getServeurType() {
        return serveurRepository.getServeurActif().getTypeServeur().name();
    }

    @PostMapping("files/upload-file-execution")
    public void uploadeExecutionFile( @Valid  @RequestBody ExecutionFile executionFile){

        fileManagerService.fileUploading( executionFile.getId(),executionFile.getTypeDossier(), executionFile.getFiles() );
    }

    @GetMapping("files/all-file")
    public ResponseEntity<List<DataFile>> getFiles(@RequestParam(name = "id") Long id, @RequestParam(name = "typeDossier") TypeDossier typeDossier){
       return ResponseEntity.ok(  ).body( fileManagerService.getEntityDataFile( id,typeDossier));
    }

    @GetMapping("files/delete")
    public void deleFile(@RequestParam(name = "id") Long id, @RequestParam(name = "typeDossier") TypeDossier typeDossier, @RequestParam(name = "fileName") String fileName){
        fileManagerService.deleteFile( id,typeDossier, fileName );
    }
}
