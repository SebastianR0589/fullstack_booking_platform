import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookingService } from '../../services/booking.service';

@Component({
  selector: 'app-booking-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './booking-form.component.html'
})
export class BookingFormComponent {
  @Input() room: any;
  @Output() bookingSuccess = new EventEmitter<void>();

  startDate: string = '';
  endDate: string = '';
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private bookingService: BookingService) {}

  submitBooking() {
    this.errorMessage = '';
    this.successMessage = '';

      const bookingPayload = {
      room: { id: this.room.id },
      user: { id: 1 }, 
      startDate: this.startDate,
      endDate: this.endDate
    };

    this.bookingService.createBooking(bookingPayload).subscribe({
      next: (res) => {
        this.successMessage = `Booking successful! Booking-ID: ${res.id}`;
        setTimeout(() => this.bookingSuccess.emit(), 2000);
      },
      error: (err) => {
            this.errorMessage = err.error || 'An unexpected error occurred.';
      }
    });
  }
}