package com.bezkoder.springjwt.serviceimpl;

import com.bezkoder.springjwt.converter.BlogConverter;
import com.bezkoder.springjwt.dto.BlogDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.models.Blog;
import com.bezkoder.springjwt.models.Hotel;
import com.bezkoder.springjwt.repository.BlogRepository;
import com.bezkoder.springjwt.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    BlogConverter converter;
    @Autowired
    BlogRepository repository;
    @Override
    public void save(BlogDTO dto) {
        Blog entity = converter.toEntity(dto);
        repository.save(entity);
    }

    @Override
    public BlogDTO update(BlogDTO dto, Long id) {
        Blog entity = new Blog();
        if(dto.getId() != null){
            Optional<Blog> blog = repository.findById(dto.getId());
            entity = converter.toEntities(dto,blog.get());
        }
       entity = repository.save(entity);
        return converter.toDTO(entity);
    }

    @Override
    public PageDTO<BlogDTO> findAllWithPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<Blog> blogList = repository.findAll(pageable);

        return PageDTO.of(blogList, blogList.stream().map(blog -> converter.toDTO(blog)).collect(Collectors.toList()));
    }

    @Override
    public void delete(Long id) {
        Blog blog = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found!!!"));
        blog.setStatus(false);
        repository.save(blog);
    }
}
