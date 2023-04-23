package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.OrderDTO;
import com.bezkoder.springjwt.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    IOrderService iOrderService;
    @PostMapping("/create")
    public OrderDTO create(@RequestBody OrderDTO dto){
        return iOrderService.save(dto);
    }
}
