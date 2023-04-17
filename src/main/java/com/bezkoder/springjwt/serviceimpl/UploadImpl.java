package com.bezkoder.springjwt.serviceimpl;

import com.bezkoder.springjwt.models.Images;
import com.bezkoder.springjwt.repository.ImageRepository;
import com.bezkoder.springjwt.service.IUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadImpl implements IUpload {
    @Autowired
    ImageRepository imageRepository;
    private final Path root = Paths.get("uploads");
    @Override
    public List<Images> storeFile(MultipartFile[] files) throws IOException {
        List<Images> images = null;
        Images image = new Images();
        try{
            images =new ArrayList<>();
            for (MultipartFile multipartFile : files){
                 String name =UUID.randomUUID().toString()+multipartFile.getOriginalFilename();

                try {
                    if(!Files.exists(root)){
                        Files.createDirectories(root);
                    }
                    Files.copy(multipartFile.getInputStream(),
                            this.root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                images.add(new Images(name,image.getData(),null));
                imageRepository.saveAll(images);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  images;
    }
    @Override
    public byte[] readContentFile(String fileName) {
        try {
            Path file = root.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return  bytes;
            }else {
                throw new RuntimeException("Can not read file " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
