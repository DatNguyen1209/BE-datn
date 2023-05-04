package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.HotelDTO;
import com.bezkoder.springjwt.dto.OrderDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.models.OrderHotelDetail;
import com.bezkoder.springjwt.repository.OrderRepository;
import com.bezkoder.springjwt.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    IOrderService iOrderService;
    @Autowired
    OrderRepository orderRepository;
    @PostMapping("/create")
    public OrderDTO create(@RequestBody OrderDTO dto){
        return iOrderService.save(dto);
    }
    @GetMapping("/getallorder")
    public List<OrderHotelDetail> getAllOrder(){
        return orderRepository.findAll();
    }
    @GetMapping("/getbyid/{id}")
    public  OrderHotelDetail getById(@PathVariable("id") Long id){
        return orderRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found!!!"));
    }
    @CrossOrigin
    @GetMapping("/getallhotel")
    public PageDTO<OrderDTO> getAllUser(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        return iOrderService.findAllWithPageable(page,size);
    }
    @PutMapping("/update/{id}")
    public OrderDTO updateOrder(@RequestBody OrderDTO dto,@PathVariable("id") Long id){
        dto.setId(id);
        return iOrderService.update(dto,id);
    }
//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable("id") Long id){
//        iOrderService.delete(id);
//    }

}
