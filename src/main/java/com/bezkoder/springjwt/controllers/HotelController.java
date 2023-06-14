package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.HotelDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.models.Hotel;
import com.bezkoder.springjwt.repository.HotelRepository;
import com.bezkoder.springjwt.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/hotel")
public class HotelController {
    @Autowired
    IHotelService iHotelService;
    @Autowired
    HotelRepository hotelRepository;
    @CrossOrigin
    @PostMapping( "/createhotel")
    public HotelDTO createHotel(@RequestBody HotelDTO dto ){
        return iHotelService.save(dto);
    }
    @CrossOrigin
    @GetMapping("/getbyid/{id}")
    public Hotel getById(@PathVariable Long id){
       return hotelRepository.findById(id).orElseThrow(() ->
           new RuntimeException("Not Found " + id)
       );
    }
    @CrossOrigin
    @GetMapping("/getall")
    public List<Hotel> getAllUserCl(){
        return  hotelRepository.findAll();
    }
    @CrossOrigin
    @GetMapping("/getallhotel")
    public PageDTO<HotelDTO> getAllHotel(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        return iHotelService.findAllWithPageable(page,size);
    }
    @CrossOrigin
    @PutMapping("updated/{id}")
    public HotelDTO updateHotel(@RequestBody HotelDTO dto, @PathVariable("id") Long id){
        dto.setId(id);
        return iHotelService.update(dto,id);
    }
    @CrossOrigin
    @PutMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id){
        iHotelService.delete(id);
    }
}
