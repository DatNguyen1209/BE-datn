package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.HotelDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.dto.RoomDTO;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.repository.RoomRepository;
import com.bezkoder.springjwt.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
     IRoomService iRoomService;
    @Autowired
    RoomRepository roomRepository;
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
    @PostMapping("/create")
    public void create(@RequestBody RoomDTO dto){
        iRoomService.save(dto);
    }
    @PostMapping("/createimg")
    public String createimg(@RequestParam("files") MultipartFile[] files, @RequestParam("id") Long id){
        return iRoomService.save(files,id);
    }
    @PutMapping("/update/{id}")
    public RoomDTO updateRoom(@RequestBody RoomDTO dto,@PathVariable("id") Long id){
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
}
