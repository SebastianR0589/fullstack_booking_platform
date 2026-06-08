import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookingService } from '../../services/booking.service';
import { BookingFormComponent } from '../booking-form/booking-form.component';

@Component({
  selector: 'app-room-list',
  standalone: true,
  imports: [CommonModule, BookingFormComponent],
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {
  rooms: any[] = [];
  selectedRoom: any = null;

  constructor(private bookingService: BookingService) {}

  ngOnInit(): void {
    this.bookingService.getRooms().subscribe({
      next: (data) => this.rooms = data,
      error: (err) => console.error('Error loading rooms', err)
    });
  }

  selectRoom(room: any) {
    this.selectedRoom = room;
  }
}