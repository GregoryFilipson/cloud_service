package com.example.CloudService.controller;

import com.example.CloudService.dto.FileDto;
import com.example.CloudService.service.FileServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping
public class FileController {

    private final FileServiceImpl fileService;

    public FileController(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

    private final String FILE_URL = "/file";

    @GetMapping(value = "/list")
    public List<FileDto> getListFiles() {
        return fileService.getAllFilesService();
    }

    @PostMapping(value = FILE_URL)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFileService(file);
    }

    @GetMapping(
            value = FILE_URL,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public byte[] getFileWithMediaType(String filename) {
        return fileService.downloadFile(filename);
    }

    @PutMapping(value = FILE_URL)
    public String editFileName(String filename, @RequestBody FileDto destFile) {
        return fileService.editFileName(filename, destFile.getFilename());
    }

    @DeleteMapping(value = FILE_URL)
    public String deleteFiles(String filename) {
        fileService.deleteFile(filename);
        return "Вы успешно удалили файл";
    }

}
