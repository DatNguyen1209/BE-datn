package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.BaseEntities;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDTO extends BaseEntities {
    private Long userId;
    private String username;
    private String fullName;
    private String phone;
    private String hotelName;
    private String roomName;
    private String price;
    private int capacity;
    private boolean status;
//    private Date dayRental;
//    private float dayNum;
//    private Long totalMoney;
    private String images;
    private Long roomId;
    private Long hotelId;

}
