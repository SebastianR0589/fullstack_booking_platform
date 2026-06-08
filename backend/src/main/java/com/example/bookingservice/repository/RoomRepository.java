package com.example.bookingservice.repository;

import com.example.bookingservice.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByCapacityGreaterThanEqual(int capacity);
}