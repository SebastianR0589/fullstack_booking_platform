package com.example.bookingservice.service;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public Booking createBooking(Booking booking) {
        if (booking.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Booking date can't be in the past!");
        }
        if (!booking.getStartDate().isBefore(booking.getEndDate())) {
            throw new IllegalArgumentException("Start date has to be before the end date!");
        }

        List<Booking> overlapping = bookingRepository.findOverlappingBookings(
                booking.getRoom().getId(),
                booking.getStartDate(),
                booking.getEndDate()
        );

        if (!overlapping.isEmpty()) {
            throw new IllegalStateException("The room is already booked!");
        }

        return bookingRepository.save(booking);
    }
}