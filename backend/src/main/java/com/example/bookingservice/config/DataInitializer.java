package com.example.bookingservice.config;

import com.example.bookingservice.model.Room;
import com.example.bookingservice.model.User;
import com.example.bookingservice.repository.RoomRepository;
import com.example.bookingservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public DataInitializer(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roomRepository.count() == 0) {
            Room r1 = new Room();
            r1.setName("Conferenceroom Alpha");
            r1.setDescription("Modern room with whiteboard and beamer.");
            r1.setPricePerNight(new BigDecimal("150.00"));
            r1.setCapacity(12);

            Room r2 = new Room();
            r2.setName("Creative studio room");
            r2.setDescription("Inspiring room with lounge chairs.");
            r2.setPricePerNight(new BigDecimal("95.00"));
            r2.setCapacity(6);

            roomRepository.save(r1);
            roomRepository.save(r2);
        }

        if (userRepository.count() == 0) {
            User u1 = new User();
            u1.setFirstName("Max");
            u1.setLastName("Mustermann");
            u1.setEmail("max@example.com");
            userRepository.save(u1);
        }
    }
}