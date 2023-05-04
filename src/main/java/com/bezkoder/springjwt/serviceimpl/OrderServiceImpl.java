package com.bezkoder.springjwt.serviceimpl;

import com.bezkoder.springjwt.converter.OrderConverter;
import com.bezkoder.springjwt.dto.OrderDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.models.OrderHotelDetail;
import com.bezkoder.springjwt.repository.OrderRepository;
import com.bezkoder.springjwt.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public PageDTO<OrderDTO> findAllWithPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<OrderHotelDetail> hotelList = orderRepository.findAll(pageable);

        return PageDTO.of(hotelList, hotelList.stream().map(order -> converter.toDTO(order)).collect(Collectors.toList()));
    }

    @Override
    public OrderDTO update(OrderDTO dto, Long id) {
        OrderHotelDetail result = new OrderHotelDetail();
        if(dto.getId() != null){
            Optional<OrderHotelDetail> orderHotelDetail = orderRepository.findById(dto.getId());
            result = converter.toEntities(dto,orderHotelDetail.get());
        }else {
            throw new RuntimeException("Not Found!!!");
        }
        result = orderRepository.save(result);
        return converter.toDTO(result);
    }

//    @Override
//    public void delete(Long id) {
//        OrderHotelDetail orderHotelDetail = orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Not Found!!!"));
//        orderHotelDetail.setDelete(true);
//        orderRepository.save(orderHotelDetail);
//
//    }

}
