package mena.gov.bf.downloadFile;

import mena.gov.bf.data.fileManager.FTPFileManagerService;
import mena.gov.bf.data.fileManager.FileManagerService;
import mena.gov.bf.data.fileManager.SFTPFileManagerService;
import mena.gov.bf.domain.enumeration.TypeDossier;
import mena.gov.bf.model.DataFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DownLoadFileService {
    private final Logger log = LoggerFactory.getLogger(DownLoadFileService.class);
    private final FileManagerService fileManagerService;

    @Autowired
    FTPFileManagerService ftpManagerService;

    @Autowired
    SFTPFileManagerService sftpManagerService;

    public DownLoadFileService(FileManagerService fileManagerService) {
        this.fileManagerService = fileManagerService;
    }

    public void uploadFiles(Long id, TypeDossier typeDossier, List<DataFile> files) {
        if (!files.isEmpty()) {
            files.forEach(dataFile -> {
                log.debug("dataFile.getFile().length =============={}", dataFile.getFile().length);
                FileOutputStream outputStream;
                try {
                    outputStream = new FileOutputStream(this.createFile(id, typeDossier, dataFile.getFileName()));
                    outputStream.write(dataFile.getFile());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void exporteFile(Long id, TypeDossier typeDossier) {
        List<DataFile> dataFiles = new ArrayList<>();
        Path path = Paths.get(this.ftpManagerService.getPath(id, typeDossier));

        try {
            Files.list(path).filter(Files::isRegularFile).forEach(file -> {
                log.debug("======================={}", file.getFileName().toString());
                DataFile dataFile = new DataFile();
                dataFile.setFileName(file.getFileName().toString());
                // dataFile.setFile( file.toA );
                dataFiles.add(dataFile);
            });

            dataFiles.forEach(val -> {
                log.debug("======================={}", val.getFileName());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return null;
    }

    private File createFile(Long id, TypeDossier typeDossier, String fileName) throws IOException {
        Path path = Paths.get(ftpManagerService.getPath(id, typeDossier));
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            log.debug("======== creation du repertoire ==========");
        }
        return new File(path + "" + fileName);
    }
}
