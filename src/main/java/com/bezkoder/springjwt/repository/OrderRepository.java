package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.OrderHotelDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderHotelDetail,Long> {
}
