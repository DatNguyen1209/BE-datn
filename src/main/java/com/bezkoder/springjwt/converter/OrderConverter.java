package com.bezkoder.springjwt.converter;

import com.bezkoder.springjwt.dto.OrderDTO;
import com.bezkoder.springjwt.models.OrderHotelDetail;
import com.bezkoder.springjwt.repository.HotelRepository;
import com.bezkoder.springjwt.repository.RoomRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class OrderConverter {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private UserRepository userRepository;
    public OrderHotelDetail toEntity(OrderDTO dto){
        OrderHotelDetail order = new OrderHotelDetail();
        order.setUser(userRepository.findByUsername(dto.getUsername()).orElseThrow(()->
             new RuntimeException("User Not Found!!!")));
        order.setUserName(order.getUser().getUsername());
        order.setFullName(order.getUser().getFullName());
        order.setEmail(order.getUser().getEmail());
        order.setPhone(order.getUser().getPhone());
        order.setCreatedDate(new Date());
        order.setStatus(dto.isStatus());
        order.setHotel(hotelRepository.getReferenceById(dto.getHotelId()));
        order.setHotelName(order.getHotel().getHotelName());
        order.setRoom(roomRepository.getReferenceById(dto.getRoomId()));
        order.setRoomName(dto.getRoomName());
        order.setPrice(order.getRoom().getPrice());
        order.setCapacity(order.getRoom().getCapacity());
        return order;
    }
    public OrderDTO toDTO(OrderHotelDetail orderHotelDetail){
        OrderDTO dto = new OrderDTO();
        if(orderHotelDetail.getId() != null){
            dto.setId(orderHotelDetail.getId());
        }
        dto.setUserId(orderHotelDetail.getId());
        dto.setUsername(orderHotelDetail.getUserName());
        dto.setFullName(orderHotelDetail.getFullName());
        dto.setHotelId(orderHotelDetail.getId());
        dto.setRoomId(orderHotelDetail.getId());
        dto.setCapacity(orderHotelDetail.getCapacity());
        dto.setPrice(orderHotelDetail.getPrice());
        dto.setPhone(orderHotelDetail.getPhone());
        dto.setRoomName(orderHotelDetail.getRoomName());
        return dto;
    }
    public OrderHotelDetail toEntities(OrderDTO dto,OrderHotelDetail orderHotelDetail){
        orderHotelDetail.setUserName(dto.getUsername());
        orderHotelDetail.setFullName(dto.getFullName());
        orderHotelDetail.setPrice(dto.getPrice());
        orderHotelDetail.setModifiedDate(new Date());
        orderHotelDetail.setPhone(dto.getPhone());
        orderHotelDetail.setCapacity(dto.getCapacity());
        return orderHotelDetail;
    }
}
