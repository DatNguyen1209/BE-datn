package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @Accessors(chain = true)
public class Room extends BaseEntities{
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "price")
    private float price;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "bed_type")
    private String bedType;
    @Column(name = "image")
    private String image;
    @Column(name = "is_status")
    private boolean isStatus = true;
    @JsonBackReference(value = "hotel-rooms")
    @ManyToOne
    @JoinColumn(name = "hotelId",referencedColumnName = "id")
    private Hotel hotelId;
    @JsonManagedReference
    @OneToMany(mappedBy = "room")
    private List<ImagesRoom> rooms;
    @JsonManagedReference
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<OrderHotelDetail> orderHotelDetail;
}
