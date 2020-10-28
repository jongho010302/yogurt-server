package com.yogurt.file;

import com.yogurt.base.exception.YogurtFileSizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FileService {

    @Value("${yogurt.file.attach.path}")
    private String attachPath;

    private final int FILE_SIZE_LIMIT = 5 * 1024 * 1024;

    public List<File> upload(String id, List<MultipartFile> files) throws Exception {

        log.info(" == uploadMultiFile ================================================ {} ", id);

        if (files.size() == 0) {
            return null;
        }

        List<File> uploadedFiles = new ArrayList<>();

        Path folder = Paths.get(attachPath, id);

        if (!Files.exists(folder)) {
            Files.createDirectory(folder);
        }

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }

            String fileFullNameOri = file.getOriginalFilename();
            String fileNameOri = fileFullNameOri.substring(0, fileFullNameOri.lastIndexOf("."));
            String fileNameExt = fileFullNameOri.lastIndexOf(".") != -1 ? fileFullNameOri.substring(fileFullNameOri.lastIndexOf(".") + 1).toLowerCase() : "";
            String fileSize = String.valueOf(file.getSize());

            log.info(" fileFullNameOri 	::: {}", fileFullNameOri);
            log.info(" fileNameExt 	::: {}", fileNameExt);
            log.info(" fileSize 	::: {}", fileSize);

            if (Integer.parseInt(fileSize) > FILE_SIZE_LIMIT) {
                throw new YogurtFileSizeException("5MB 이하의 파일을 첨부해 주세요.");
            }

            // 해당 파일 서버에 저장
            File uploadedFile = new File(attachPath + File.separator + id + File.separator + fileNameOri + "." + fileNameExt);
            uploadedFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(uploadedFile);
            fos.write(file.getBytes());
            fos.close();

            uploadedFiles.add(uploadedFile);
        }

        log.info(" ============================================================ ");

        return uploadedFiles;
    }
}
