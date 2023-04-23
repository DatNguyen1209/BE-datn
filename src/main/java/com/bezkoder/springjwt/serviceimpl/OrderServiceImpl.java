package com.bezkoder.springjwt.serviceimpl;

import com.bezkoder.springjwt.converter.OrderConverter;
import com.bezkoder.springjwt.dto.OrderDTO;
import com.bezkoder.springjwt.models.OrderHotelDetail;
import com.bezkoder.springjwt.repository.OrderRepository;
import com.bezkoder.springjwt.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderConverter converter;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDTO save(OrderDTO dto) {
        OrderHotelDetail order = null;
        try {
             order = converter.toEntity(dto);
             var result = orderRepository.save(order);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return converter.toDTO(order);
    }
}
