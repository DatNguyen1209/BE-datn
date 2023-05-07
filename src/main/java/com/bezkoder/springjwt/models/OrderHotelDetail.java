package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_hotel_detail")
@Getter
@Setter
public class OrderHotelDetail extends BaseEntities{

    @Column(name = "user_name")
    private String username;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "price")
    private float price;
    @Column(name = "email")
    private String email;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "image")
    private String image;
    @Column(name = "day_rental")
    private Date dayRental;
    @Column(name = "is_confirm")
    private Boolean isConfirm = null;
    @Column(name = "day_num")
    private float dayNum;
    @Column(name = "total_money")
    private Long totalMoney ;
    @Column(name = "status")
    private boolean status = false;
    @Column(name = "is_delete")
    private boolean isDelete  = true;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_order",referencedColumnName = "id")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "id")
    private Room room;
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel",referencedColumnName = "id")
    private Hotel hotel;


}
