import { Component, OnInit } from '@angular/core';
import { AxiosService } from '../../Services/axios.service';
import { NavigationBarComponent } from '../navigation-bar/navigation-bar.component';
import { SearchBarComponent } from '../search-bar/search-bar.component';

export interface House {
  id: string;
  title: string;
  price: number | null;
  description: string;
  rating: number | null;
  location: string;
  images: Image;
  registerDate: string; // ISO date string
}

export interface Image {
  imageName: string[];
  imageType: string[];
  src: string[]; // Base64 encoded strings
}

@Component({
  selector: 'Homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
  imports: [NavigationBarComponent, SearchBarComponent],
})
export class HomepageComponent implements OnInit {
  houses: House[] = [];

  constructor(private axiosService: AxiosService) {}

  ngOnInit(): void {
    this.axiosService
      .request('GET', '/house/all', {})
      .then((response) => {
        this.houses = response.data.map((house: any) => {
          return {
            ...house,
            images: {
              imageName: house.images.map((image: any) => image.imageName),
              imageType: house.images.map((image: any) => image.imageType),
              src: house.images.map(
                (image: any) =>
                  `data:${image.imageType};base64,${image.images}`
              ),
            },
          };
        });
        console.log(this.houses);
      })
      .catch((error) => console.error('Error fetching houses:', error));
  }
}
