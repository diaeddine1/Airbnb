// import { Component } from '@angular/core';
// import { AxiosService } from '../../Services/axios.service';
// import { FormsModule } from '@angular/forms';
// import { Image } from '../homepage/homepage.component';
// export interface AddedHouse{

//   title: string;
//   price: number | null;
//   description: string;
//   rating: number | null;
//   location: string;
//   files: File[];
//   registerDate: string; // ISO date string
// }

// @Component({
//   selector: 'add-house',
//   imports: [FormsModule],
//   templateUrl: './add-house.component.html',
//   styleUrls: ['./add-house.component.css'],
// })



// export class AddHouseComponent {
//   // title: string = '';
//   // description: string = '';
//   // location: string = '';
//   // price: number = 0;
//   // image: File | null = null;
  
//   addedHouse:AddedHouse={
//     title: '',
//     price: null,
//     description: '',
//     rating: null,
//     location: '',
//     files: [],
//     registerDate: ''
//   }
  
//   constructor(private axiosService: AxiosService) {}

//   // Handle file input change
//   handleFileInput(event: Event): void {
//     const target = event.target as HTMLInputElement;
//     if (target.files && target.files.length > 0) {
//       for(let i = 0 ;i<target.files.length;i++){
//         this.addedHouse.files[i]=target.files[i];
 
//       }
//       // this.image = target.files[0];
//     }
//     console.log(this.addedHouse.files)
//   }

//   Save(event: Event): void {
//     event.preventDefault();
//     if (!this.addedHouse.files) {
//       console.error('No image file selected');
//       return;
//     }

//     const formData = new FormData();
//     formData.append(
//       'house',
//       new Blob(
//         [
//           JSON.stringify({
//             title: this.addedHouse.title,
//             description: this.addedHouse.description,
//             price: this.addedHouse.price,
//             rating: 5,
//             location: this.addedHouse.location,
//             registerDate : Date.now()
//           }),
//         ],
//         { type: 'application/json' }
//       )
//     );
//     for(let i = 0 ;i<this.addedHouse.files.length;i++){
//       //this.addedHouse.images[i]=target.files[i];
//       formData.append('files', this.addedHouse.files[i]);
//     }
//     //formData.append('file', this.addedHouse.images);
  
//     this.axiosService
//       .request('POST', '/house/add', formData,{'Content-Type': 'multipart/form-data',}) ///house/add
//       .then((response) => {
//         console.log('House saved successfully:', response.data);
//       })
//       .catch((error) => {
//         console.error('Error saving house:', error);
//       });
//   }
// }
import { Component, OnInit } from '@angular/core';
import { AxiosService } from '../../Services/axios.service';
import { FormsModule } from '@angular/forms';

import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { OSM, XYZ } from 'ol/source';
import TileLayer from 'ol/layer/Tile';
import { fromLonLat } from 'ol/proj';

// Define interface for the house metadata
export interface AddedHouse {
  title: string;
  price: number | null;
  description: string;
  rating: number | null;
  location: Location;
  numberOfRooms:number,
  numberOfFloors:number,
  numberOfBathrooms:number,
  maximumNumberOfGuests:number,
  files: File[]; // Images files to be uploaded
  registerDate: string; // ISO date string
}


export interface Location {
  country:string,
  city:string,
  street:string
}

// export interface Position{
//   latitude:string | null,
//   longitude:string | null,

// }

@Component({
  selector: 'add-house',
  imports: [FormsModule],
  templateUrl: './add-house.component.html',
  styleUrls: ['./add-house.component.css'],
})
export class AddHouseComponent implements OnInit{
  // Initialize the house metadata
  addedHouse: AddedHouse = {
    title: '',
    price: null,
    description: '',
    rating: null,
    location: { country: '', city: '', street: '' },  
    numberOfRooms:0,
    numberOfFloors:0,
    numberOfBathrooms:0,
    maximumNumberOfGuests:0,
    files: [],
    registerDate: '',
  };

  constructor(private axiosService: AxiosService) {}
  

  // Handle file input change (when images are selected)
  handleFileInput(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.files && target.files.length > 0) {
      // Update the files array with selected images
      this.addedHouse.files = Array.from(target.files);
    }
    console.log(this.addedHouse.files);
  }

  Save(event: Event): void {
    event.preventDefault();
  
    if (!this.addedHouse.files || this.addedHouse.files.length === 0) {
      console.error('No image file selected');
      return;
    }

    // Create a FormData object to send both house metadata and images
    const formData = new FormData();

    // Append house metadata as a JSON string
    formData.append('house', JSON.stringify({
      title: this.addedHouse.title,
      price: this.addedHouse.price,
      description: this.addedHouse.description,
      rating: this.addedHouse.rating,
      location:{
        country:this.addedHouse.location.country,
        city:this.addedHouse.location.city,
        street:this.addedHouse.location.street
      },
      numberOfRooms:this.addedHouse.numberOfRooms,
      numberOfFloors:this.addedHouse.numberOfFloors,
      numberOfBathrooms:this.addedHouse.numberOfBathrooms,
      maximumNumberOfGuests:this.addedHouse.maximumNumberOfGuests,
      registerDate: new Date().toISOString(),
    }));

    // Append image files to the FormData
    for (let i = 0; i < this.addedHouse.files.length; i++) {
      formData.append('files', this.addedHouse.files[i], this.addedHouse.files[i].name); // Use 'files' as the name
    }

    this.axiosService
      .request('POST', '/house/add', formData)  // No need to specify Content-Type
      .then((response) => {
        console.log('House saved successfully:', response.data);
      })
      .catch((error) => {
        console.error('Error saving house:', error);
      });
  }

  lat:any
  lng:any
  async getLocation():Promise<void>{
    return new Promise((resolve,reject)=>{
      if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition((position)=>{
        if(position){
          console.log("Latitude : " + position.coords.latitude + "Longitude :" + position.coords.longitude)
          this.lat=position.coords.latitude 
          this.lng = position.coords.longitude
          resolve()
        }
        
  
      },(error)=>{
        console.log(error);
        reject(error);
        })
      }else{
        console.log("Geolocation is not supported by this browser")
        reject("Geolocation is not supported by this browser")
      }
    })

    }
    

  public map!: Map
  async initMap():Promise<void>{
   try {
     await this.getLocation()
     this.map = new Map({
       layers: [
         new TileLayer({
           source: new OSM(),
         }),
       ],
       target: 'map',
       view: new View({ 
         // center: [this.lat, this.lng],
         // center: [30.3771155 , -9.5141502],
         center: fromLonLat([this.lng, this.lat]),
         zoom: 15,maxZoom: 28, 
       }),
     });
   } catch (error) {

    
    
   }
  }
  ngOnInit(): void {
    this.initMap()
    // throw new Error('Method not implemented.');
    
  }
  
}
