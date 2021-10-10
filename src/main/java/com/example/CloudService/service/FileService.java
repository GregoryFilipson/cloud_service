package com.example.CloudService.service;

import com.example.CloudService.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    byte[] downloadFile(String fileName);
    int deleteFile(String fileName);
    String uploadFileService (MultipartFile file);
    List<FileDto> getAllFilesService();
    String editFileName(String nameFileWhichWeWantToEdit, String newFileName);
}
