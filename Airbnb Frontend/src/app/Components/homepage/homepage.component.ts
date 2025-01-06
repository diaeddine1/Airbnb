import { Component, OnInit } from '@angular/core';
import { AxiosService } from '../../Services/axios.service';
import { NavigationBarComponent } from '../navigation-bar/navigation-bar.component';
import { SearchBarComponent } from '../search-bar/search-bar.component';
import { CarouselModule } from 'primeng/carousel';
import { ButtonModule } from 'primeng/button';
import { NgFor, NgIf } from '@angular/common';

// Location Interface
export interface Location {
  country: string;
  city: string;
  street: string;
}

// Image Interface
export interface Image {
  imageName: string;
  imageType: string;
  src: string; // Base64 encoded string
}

// House Interface
export interface House {
  id: string;
  title: string;
  price: number | null;
  description: string;
  rating: number | null;
  location: Location;
  numberOfRooms: number;
  numberOfFloors: number;
  numberOfBathrooms: number;
  maximumNumberOfGuests: number;
  images: Image[];
  registerDate: string; // ISO date string
}

@Component({
  selector: 'Homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
  imports: [NgIf,NgFor,NavigationBarComponent, SearchBarComponent, CarouselModule, ButtonModule],
})
export class HomepageComponent implements OnInit {
  houses: House[] = []; // Initialize as an empty array

  constructor(private axiosService: AxiosService) {}

  ngOnInit(): void {
    this.axiosService
      .request('GET', '/house/all', {})
      .then((response) => {
        // Ensure the response contains the correct data structure
        this.houses = response.data.map((house: any) => {
          return {
            ...house,
            images: house.images.map((image: any) => ({
              imageName: image.imageName,
              imageType: image.imageType,
              // Properly generate the base64 image src
              src: `data:${image.imageType};base64,${image.imageByte}`,
            })),
          };
        });

        console.table(this.houses); // Log the whole `houses` array for debugging
      })
      .catch((error) => console.error('Error fetching houses:', error));
  }
}
