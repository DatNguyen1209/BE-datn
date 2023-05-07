package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.OrderDTO;
import com.bezkoder.springjwt.dto.PageDTO;

public interface IOrderService {
    OrderDTO save (OrderDTO dto);
    PageDTO<OrderDTO> findAllWithPageable(int page , int size);
    OrderDTO update(OrderDTO dto, Long id);
    void confirm (Long id);
    void cancel(Long id);
//    void delete(Long id);

}
