package com.example.CloudService.controller;

import com.example.CloudService.service.FileService;
import com.example.CloudService.dto.FileDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {

    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    private final String ROOT_URL = "/";

    @GetMapping(value = "/list")
    public List<FileDto> getListFiles() {
        return fileService.getAllFilesService();
    }

    @PostMapping(value = "/upload")
    public @ResponseBody
    String handleFileUpload(@RequestParam("name") String name,
                            @RequestParam("file") MultipartFile file) {
        return fileService.uploadFileService(file, name);
    }


    @GetMapping(
            value = "/getFiles",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] getFileWithMediaType(String fileName) throws IOException {
        return fileService.downloadFileService(fileName);
    }

    @PutMapping (value = ROOT_URL)
    public @ResponseBody String editFileName (String nameFileWhichWeWantToEdit, String newFileName) {
        return fileService.editFileName(nameFileWhichWeWantToEdit, newFileName);
    }

    @DeleteMapping(value = ROOT_URL)
    public Object deleteFiles(String fileName) {
        return fileService.deleteFileService(fileName);
    }
}
