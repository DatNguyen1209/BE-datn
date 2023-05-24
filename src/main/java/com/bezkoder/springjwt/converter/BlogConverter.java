package com.bezkoder.springjwt.converter;

import com.bezkoder.springjwt.dto.BlogDTO;
import com.bezkoder.springjwt.models.Blog;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BlogConverter {
    public Blog toEntity(BlogDTO dto){
        Blog entity = new Blog();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        entity.setCreatedDate(new Date());
        return entity;
    }
    public BlogDTO toDTO(Blog entity){
        BlogDTO dto = new BlogDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }
    public Blog toEntities(BlogDTO dto,Blog entity){
        entity.setTitle(dto.getTitle());
        entity.setImage(dto.getImage());
        entity.setContent(dto.getContent());
        entity.setModifiedDate(new Date());
        return entity;
    }
}
