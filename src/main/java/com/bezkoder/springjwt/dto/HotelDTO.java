package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.BaseEntities;
import com.bezkoder.springjwt.models.Images;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO extends BaseEntities {
    private Long hotelId;
    private String hotelName;
    private String phone;
    private String address;
    private float rated;
    private int viewers;
    private String fromPrice;
    private String description;
    private String hotelType;
    private String image;
    private String service;
    private Boolean status;

}
