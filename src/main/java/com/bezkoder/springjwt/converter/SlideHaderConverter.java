package com.bezkoder.springjwt.converter;

import com.bezkoder.springjwt.dto.SlideHeaderDTO;
import com.bezkoder.springjwt.models.SlideHeader;
import org.springframework.stereotype.Component;

@Component
public class SlideHaderConverter {
    public SlideHeader toEntity(SlideHeaderDTO dto){
        SlideHeader entity  = new SlideHeader();
        entity.setTitle(dto.getTitle());
        entity.setImages(dto.getImages());
        return entity;
    }
    public SlideHeader toEntities(SlideHeader slideHeader , SlideHeaderDTO dto){
        slideHeader.setTitle(dto.getTitle());
        slideHeader.setImages(dto.getImages());
        return slideHeader;
    }
    public SlideHeaderDTO toDTO(SlideHeader entity){
        SlideHeaderDTO dto = new SlideHeaderDTO();
        dto.setTitle(entity.getTitle());
        dto.setImages(entity.getImages());
        return dto;
    }
}
