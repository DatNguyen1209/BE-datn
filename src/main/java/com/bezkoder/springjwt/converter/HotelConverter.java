package com.bezkoder.springjwt.converter;

import com.bezkoder.springjwt.dto.HotelDTO;
import com.bezkoder.springjwt.models.Hotel;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HotelConverter {
    public Hotel toEntities(HotelDTO dto){
        Hotel entity = new Hotel();
        entity.setImage(dto.getImage());
        entity.setHotelName(dto.getHotelName());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setRated(dto.getRated());
        entity.setCreatedDate(new Date());
        entity.setFromPrice(dto.getFromPrice());
        entity.setDescription(dto.getDescription());
        entity.setHotelType(dto.getHotelType());
        entity.setViewers(dto.getViewers());
        entity.setService(dto.getService());
//        entity.setImages(dto.getImages());
        return entity;
    }
    public HotelDTO toDTO(Hotel hotel){
        HotelDTO dto = new HotelDTO();
        if(hotel.getId() != null){
            dto.setId(hotel.getId());
        }
        dto.setImage(hotel.getImage());
        dto.setHotelId(hotel.getId());
        dto.setHotelName( hotel.getHotelName());
        dto.setHotelType(hotel.getHotelType());
        dto.setPhone(hotel.getPhone());
        dto.setAddress(hotel.getAddress());
        dto.setCreatedDate(hotel.getCreatedDate());
        dto.setModifiedDate(hotel.getModifiedDate());
        dto.setRated(hotel.getRated());
        dto.setDescription(hotel.getDescription());
        dto.setFromPrice(hotel.getFromPrice());
        dto.setViewers(hotel.getViewers());
        dto.setService(hotel.getService());
        dto.setStatus(hotel.isStatus());
        return dto;
    }
    public Hotel toEntities(HotelDTO dto, Hotel hotel){
        hotel.setHotelName(dto.getHotelName());
        hotel.setPhone(dto.getPhone());
        hotel.setModifiedDate(new Date());
        hotel.setAddress(dto.getAddress());
        hotel.setFromPrice(dto.getFromPrice());
        hotel.setRated(dto.getRated());
        hotel.setService(dto.getService());
        hotel.setDescription(dto.getDescription());
        hotel.setHotelType(dto.getHotelType());
        hotel.setImage(dto.getImage());
        hotel.setService(dto.getService());
        hotel.setViewers(dto.getViewers());
        return hotel;
    }
}
