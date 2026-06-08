import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {RoomListComponent} from "./components/room-list/room-list.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RoomListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
