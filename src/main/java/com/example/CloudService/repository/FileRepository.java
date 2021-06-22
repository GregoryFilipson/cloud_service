package com.example.CloudService.repository;

import com.example.CloudService.model.File;
import com.example.CloudService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    List<File> findByUserId(Long userId);
    Integer deleteFileByFileNameAndUserId(String fileName, Long userId);
    File findFileByFileNameAndUserId(String name, Long userId);
    File findFileByFileName(String fileName);
}
