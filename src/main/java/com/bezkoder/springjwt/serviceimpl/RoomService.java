package com.bezkoder.springjwt.serviceimpl;

import com.bezkoder.springjwt.converter.RoomConverter;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.dto.RoomDTO;
import com.bezkoder.springjwt.models.Hotel;
import com.bezkoder.springjwt.models.ImagesRoom;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.repository.HotelRepository;
import com.bezkoder.springjwt.repository.ImageRoomRepository;
import com.bezkoder.springjwt.repository.RoomRepository;
import com.bezkoder.springjwt.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {
    private final Path root = Paths.get("datn/uploads");
    @Autowired
    private RoomConverter converter;
    @Autowired
    private ImageRoomRepository imageRoomRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public void save(RoomDTO dto) {
        try {
            Room room = null;
                room = new Room();
                room.setId(dto.getRoomId());
                room.setRoomName(dto.getRoomName());
                room.setPrice(dto.getPrice());
                room.setImage(dto.getImage());
                room.setCapacity(dto.getCapacity());
                room.setCreatedDate(new Date());
                room.setBedType(dto.getBedType());
                room.setStatus(dto.isStatus());
                room.setHotelId(hotelRepository.getReferenceById(dto.getHotelId()));
                roomRepository.save(room);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public PageDTO<RoomDTO> findAllWithPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<Room> roomList = roomRepository.findAll(pageable);

        return PageDTO.of(roomList, roomList.stream().map(room -> converter.toDTO(room)).collect(Collectors.toList()));
    }

    @Override
    public String save(MultipartFile[] files, Long id) {
        try {
            List<ImagesRoom> imagesRooms = new ArrayList<>();
            for (MultipartFile multipartFile: files){
                String name = UUID.randomUUID().toString()+multipartFile.getOriginalFilename();
                Room room = new Room();
                room.setId(id);
                try {
                    if(Files.exists(root) == false){
                        Files.createDirectories(root);
                    }
                    Files.copy(multipartFile.getInputStream(),
                            this.root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                imagesRooms.add(new ImagesRoom(null,name,room));
            }
            imageRoomRepository.saveAll(imagesRooms);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
        return "Oki";
    }

    @Override
    public RoomDTO updateRoom(RoomDTO dto, Long id) {
        Room result =new Room();
        if(dto.getId() != null){
            Optional<Room> room = roomRepository.findById(dto.getId());
            result = converter.toEntities(dto,room.get());
        }else {
            throw new RuntimeException("Not Found!!!");
        }
        result = roomRepository.save(result);
        return converter.toDTO(result);
    }

    @Override
    public RoomDTO updateRoomImg(MultipartFile[] files, Long id) {
        Room room = roomRepository.findById(id).orElseThrow(()->{
            throw  new RuntimeException("Not Found Room");
        });
        if(room.getRooms() != null){
            room.getRooms().stream().forEach((i)->{
                try {
                    imageRoomRepository.deleteById(i.getId());
                    Files.deleteIfExists(this.root.resolve(i.getUrl()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        List<ImagesRoom> imagesRooms = new ArrayList<>();
        for (MultipartFile multipartFile: files){
            String name = UUID.randomUUID().toString()+multipartFile.getOriginalFilename();
            try {
                if(Files.exists(root) == false){
                    Files.createDirectories(root);
                }
                Files.copy(multipartFile.getInputStream(),
                        this.root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            imagesRooms.add(new ImagesRoom(null,name,room));
        }
        imageRoomRepository.saveAll(imagesRooms);
        return converter.toDTO(room);
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("Not Found!!!");
        });
    }



    @Override
    public void deleteById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("Not Found!!!");
        });
        room.setStatus(false);
        roomRepository.save(room);
    }

    @Override
    public void restore(Long id) {
        Room room = this.findById(id);
        room.setStatus(true);
        roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRoom() {

        return roomRepository.findAll();
    }

    public List<Room> lstRoomByHotelId(Long hotelId){
//        try {
            var res = roomRepository.findAll()
                    .stream().filter(s -> {
                        return s.getHotelId() == hotelRepository.getReferenceById(hotelId);
                    }).toList();
            return res;
//        } catch (Exception e){
//            ret
//        }
    }
}
