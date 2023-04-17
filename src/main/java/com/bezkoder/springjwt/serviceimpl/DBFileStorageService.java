package com.bezkoder.springjwt.serviceimpl;

import com.bezkoder.springjwt.models.File;
import com.bezkoder.springjwt.payload.exception.FileStorageException;
import com.bezkoder.springjwt.payload.exception.MyFileNotFoundException;
import com.bezkoder.springjwt.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class DBFileStorageService {

    @Autowired
    private FileRepository fileRepository;

    public File storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(UUID.randomUUID().toString() + file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File dbFile = new File(fileName, file.getContentType(), file.getBytes());

            return fileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public File getFile(String fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}