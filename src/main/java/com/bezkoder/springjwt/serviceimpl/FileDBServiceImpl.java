package com.bezkoder.springjwt.serviceimpl;

import com.bezkoder.springjwt.models.Images;
import com.bezkoder.springjwt.repository.ImageRepository;
import com.bezkoder.springjwt.service.FileDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;
@Service
public class FileDBServiceImpl implements FileDBService {
    @Autowired
    private ImageRepository imageRepository;

//    @Override
//    public Images store(MultipartFile file) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        Images image = new Images(fileName, file.getContentType(), file.getBytes());
//
//        return imageRepository.save(image);
//    }

    @Override
    public Stream<Images> getAllFiles() {
        return imageRepository.findAll().stream();
    }
}
