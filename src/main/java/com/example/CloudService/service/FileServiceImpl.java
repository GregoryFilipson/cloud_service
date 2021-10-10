package com.example.CloudService.service;

import com.example.CloudService.dto.FileDto;
import com.example.CloudService.model.File;
import com.example.CloudService.repository.FileRepository;
import com.example.CloudService.security.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final AuthorizationService authorizationService;
    private final ConversionService conversionService;

    private final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    public FileServiceImpl(FileRepository fileRepository,
                           AuthorizationService authorizationService,
                           ConversionService conversionService) {
        this.fileRepository = fileRepository;
        this.authorizationService = authorizationService;
        this.conversionService = conversionService;
    }

    public byte[] downloadFile(String fileName) {
        Long userId = authorizationService.getUserId();
        return fileRepository.findFileByFileNameAndUserId(fileName, userId).getData();
    }

    public int deleteFile(String fileName) {
       return fileRepository.deleteFileByFileNameAndUserId(fileName, authorizationService.getUserId());
    }

    public String uploadFileService (MultipartFile file) {
        if (file.isEmpty()) {
            LOG.error("Can't upload, file is empty.");
            return "Вам не удалось загрузить " + file.getOriginalFilename()+ " потому что файл пустой.";
        }

        try {
            File fileEntity = new File();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setData(file.getBytes());
            fileEntity.setUserId(authorizationService.getUserId());
            fileRepository.save(fileEntity);
            return "Вы удачно загрузили " + file.getOriginalFilename();
        } catch (Exception e) {
            return "Вам не удалось загрузить " + file.getOriginalFilename() + " => " + e.getMessage();
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
}
