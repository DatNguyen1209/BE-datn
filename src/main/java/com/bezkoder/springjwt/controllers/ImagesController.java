package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Images;
import com.bezkoder.springjwt.payload.response.FileDBResponse;
import com.bezkoder.springjwt.repository.ImageRepository;
import com.bezkoder.springjwt.service.FileDBService;
import com.bezkoder.springjwt.service.IUpload;
import com.bezkoder.springjwt.serviceimpl.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/uploads")
public class ImagesController {
    @Autowired
    private HotelService service;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    FileDBService fileDBService;

    @Autowired
    IUpload upload;
    @GetMapping("/getfile/{fileName}")
    public ResponseEntity<byte[]> readFile(@PathVariable String fileName){
        try {
            byte[] bytes = upload.readContentFile(fileName);
            return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<FileDBResponse>> getListFiles() {
        List<FileDBResponse> files = fileDBService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/uploads/files/")
                    .path(dbFile.getUrl().toString())
                    .toUriString();

            return new FileDBResponse(
                    fileDownloadUri);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    @PostMapping("/uploadfiles")
    public List<Images> uploadfile(@RequestParam("files")MultipartFile[] file) throws IOException {
        return upload.storeFile(file);
    }
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<Images> optionalFileDB =imageRepository.findById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" )
                .body(optionalFileDB.get().getData());
    }

}
