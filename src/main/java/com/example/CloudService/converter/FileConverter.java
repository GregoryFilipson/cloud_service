package com.example.CloudService.converter;

import com.example.CloudService.model.File;
import com.example.CloudService.dto.FileDto;
import org.springframework.core.convert.converter.Converter;

public class FileConverter implements Converter<File, FileDto> {

    @Override
    public FileDto convert(File file) {
        FileDto fileDto = new FileDto();
        fileDto.setFilename(file.getFileName());
        fileDto.setSize(file.getData().length);
        return fileDto;
    }
}
