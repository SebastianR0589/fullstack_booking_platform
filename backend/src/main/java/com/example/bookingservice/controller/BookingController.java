package com.example.bookingservice.controller;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
public class BookingController {

    @Autowired
    private BookingService bookingPhaseService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        try {
            Booking savedBooking = bookingPhaseService.createBooking(booking);
            return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (jakarta.persistence.OptimisticLockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Please try again");
        }
    }
}