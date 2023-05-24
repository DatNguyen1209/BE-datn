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
//        order.setDayNum(dto.getDayNum());
//        order.setDayRental(dto.getDayRental());
//        order.setTotalMoney(dto.getTotalMoney());
        order.setUser(userRepository.getReferenceById(dto.getUserId()));
        order.setUsername(order.getUser().getUsername());
        order.setFullName(order.getUser().getFullName());
        order.setEmail(order.getUser().getEmail());
        order.setPhone(order.getUser().getPhone());
        order.setCreatedDate(new Date());
        order.setStatus(dto.isStatus());
        order.setHotel(hotelRepository.getReferenceById(dto.getHotelId()));
        order.setHotelName(order.getHotel().getHotelName());
        order.setRoom(roomRepository.getReferenceById(dto.getRoomId()));
        order.setRoomName(order.getRoom().getRoomName());
        order.setPrice(order.getRoom().getPrice());
        order.setImage(order.getRoom().getImage());
        order.setDelete(true);
        order.setDayRental(dto.getDayRental());
        order.setDayNum(dto.getDayNum());
        order.setTotalMoney(dto.getTotalMoney());
        order.setCapacity(order.getRoom().getCapacity());
        return order;
    }
    public OrderDTO toDTO(OrderHotelDetail orderHotelDetail){
        OrderDTO dto = new OrderDTO();
        if(orderHotelDetail.getId() != null){
            dto.setId(orderHotelDetail.getId());
        }
        dto.setUserId(orderHotelDetail.getId());
        dto.setUsername(orderHotelDetail.getUsername());
        dto.setFullName(orderHotelDetail.getFullName());
        dto.setHotelId(orderHotelDetail.getId());
        dto.setRoomId(orderHotelDetail.getId());
        dto.setCapacity(orderHotelDetail.getCapacity());
        dto.setPrice(orderHotelDetail.getPrice());
        dto.setPhone(orderHotelDetail.getPhone());
        dto.setRoomName(orderHotelDetail.getRoomName());
        dto.setDelete(orderHotelDetail.isDelete());
        dto.setCreatedDate(orderHotelDetail.getCreatedDate());
        dto.setModifiedDate(orderHotelDetail.getModifiedDate());
        dto.setImage(orderHotelDetail.getImage());
        dto.setHotelName(orderHotelDetail.getHotelName());
        dto.setDayRental(orderHotelDetail.getDayRental());
        dto.setDayNum(orderHotelDetail.getDayNum());
        dto.setTotalMoney(orderHotelDetail.getTotalMoney());
        dto.setDayNum(orderHotelDetail.getDayNum());
        dto.setIsConfirm(orderHotelDetail.getIsConfirm());
        dto.setDayRental(orderHotelDetail.getDayRental());
        dto.setTotalMoney(orderHotelDetail.getTotalMoney());
        dto.setStatus(orderHotelDetail.isStatus());
        return dto;
    }
    public OrderHotelDetail toEntities(OrderDTO dto,OrderHotelDetail orderHotelDetail){
        orderHotelDetail.setModifiedDate(new Date());
        orderHotelDetail.setDayNum(dto.getDayNum());
        orderHotelDetail.setDayRental(dto.getDayRental());
        orderHotelDetail.setTotalMoney(dto.getTotalMoney());
        return orderHotelDetail;
    }
}
