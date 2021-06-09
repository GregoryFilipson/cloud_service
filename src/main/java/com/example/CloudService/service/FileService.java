package com.example.CloudService.service;

import com.example.CloudService.model.File;
import com.example.CloudService.repository.FileRepository;
import com.example.CloudService.security.AuthorizationService;
import com.example.CloudService.dto.FileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class FileService {

    private final FileRepository fileRepository;
    private final AuthorizationService authorizationService;
    private final ConversionService conversionService;

    private final Logger LOG = LoggerFactory.getLogger(FileService.class);

    public FileService( FileRepository fileRepository, AuthorizationService authorizationService, ConversionService conversionService) {
        this.fileRepository = fileRepository;
        this.authorizationService = authorizationService;
        this.conversionService = conversionService;
    }

    public byte[] downloadFileService(String fileName) throws IOException {
        Long userId = authorizationService.getUserId();
        return fileRepository.findFileByFileNameAndUserId(fileName, userId).getData();
    }

    public String deleteFileService(String fileName) {
        fileRepository.deleteFileByFileNameAndUserId(fileName, authorizationService.getUserId());
        return "Файл успешно удален";
    }

    public String uploadFileService (MultipartFile file, String fileName) {
        if (file.isEmpty()) {
            LOG.error("Can't upload, file is empty.");
            return "Вам не удалось загрузить " + file.getName() + " потому что файл пустой.";
        }

        try {
            File fileEntity = new File();
            fileEntity.setFileName(fileName);
            fileEntity.setData(file.getBytes());
            fileEntity.setUserId(authorizationService.getUserId());
            fileRepository.save(fileEntity);
            return "Вы удачно загрузили " + file.getName();
        } catch (Exception e) {
            return "Вам не удалось загрузить " + file.getName() + " => " + e.getMessage();
        }
    }

    public List<FileDto> getAllFilesService() {
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(File.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(FileDto.class));
        Long userId = authorizationService.getUserId();
       return (List<FileDto>) conversionService.convert(fileRepository.findByUserId(userId),
               sourceType, targetType);
    }

    public String editFileName(String nameFileWhichWeWantToEdit, String newFileName){
        fileRepository.findFileByFileName(nameFileWhichWeWantToEdit).setFileName(newFileName);
        return "Имя файла изменено";
    }

//    private String getPathToUserFile(String filename) {
//        return pathToFolder.concat(authorizationService.getUserId().toString())
//                .concat("/")
//                .concat(filename);
//    }
}
