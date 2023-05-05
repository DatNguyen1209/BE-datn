package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.converter.RoomConverter;
import com.bezkoder.springjwt.dto.HotelDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.dto.RoomDTO;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.repository.RoomRepository;
import com.bezkoder.springjwt.service.IRoomService;
import com.bezkoder.springjwt.serviceimpl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
     IRoomService iRoomService;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomConverter converter;

    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/getallroom")
    public PageDTO<RoomDTO> getAllUser(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        return iRoomService.findAllWithPageable(page,size);
    }
    @GetMapping("/getbyid/{id}")
    public Room  getById(@PathVariable("id") Long id){
        return roomRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Not Found!!!");
        });
    }
    @GetMapping("/findallbyid/{id}")
    public List<Room> findAllById(@PathVariable("id") Long id){
        Room room = new Room();
        if( room.isStatus() == true && room.getHotelId().getId() == id){
            roomRepository.findAll();
        }
        else {
            System.out.println("Id or Status is false");
        }
        return (List<Room>) room;
    }
    @PostMapping("/create")
    public void create(@RequestBody RoomDTO dto){
        iRoomService.save(dto);
    }
    @PostMapping("/createimg")
    public String createimg(@RequestParam("files") MultipartFile[] files, @RequestParam("id") Long id){
        return iRoomService.save(files,id);
    }
    @PutMapping("/update/{id}")
    public RoomDTO updateRoom(@RequestBody RoomDTO dto,@PathVariable Long id){
        dto.setId(id);
        return iRoomService.updateRoom(dto,id);
    }
    @PutMapping("/updateroomimg/{id}")
    public RoomDTO updateRoomImg(@RequestParam("files") MultipartFile[] files,@PathVariable("id") Long id){
        return iRoomService.updateRoomImg(files,id);
    }
    @PutMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        iRoomService.deleteById(id);
    }
    @PutMapping("/restore/{id}")
    public void  restore(@PathVariable Long id){
        iRoomService.restore(id);
    }

    @GetMapping("/getLstRoomByHotelId/{hotelId}")
    public List<Room> lstRoomByHotelId(@PathVariable Long hotelId){
        return roomService.lstRoomByHotelId(hotelId);
    }
}
