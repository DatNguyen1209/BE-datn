package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM Room WHERE room_name = ?1")
    Hotel findRoomCurrentByName(String roomName);

}
