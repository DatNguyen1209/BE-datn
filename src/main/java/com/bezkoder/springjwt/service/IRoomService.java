package com.bezkoder.springjwt.service;


import com.bezkoder.springjwt.dto.HotelDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.dto.RoomDTO;
import com.bezkoder.springjwt.models.Room;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.util.List;

public interface IRoomService {
    void save(RoomDTO dto);
    PageDTO<RoomDTO> findAllWithPageable(int page, int size);
    String save(MultipartFile[] files,Long id);
    RoomDTO updateRoom(RoomDTO dto, Long id);
    RoomDTO updateRoomImg(MultipartFile[] files, Long id);
    Room findById(Long id);
    void deleteById(Long id);
    void restore(Long id);
    List<Room> getAllRoom();
}
