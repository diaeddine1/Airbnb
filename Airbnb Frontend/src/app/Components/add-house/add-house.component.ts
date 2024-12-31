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
import { Component } from '@angular/core';
import { AxiosService } from '../../Services/axios.service';
import { FormsModule } from '@angular/forms';

// Define interface for the house metadata
export interface AddedHouse {
  title: string;
  price: number | null;
  description: string;
  rating: number | null;
  location: string;
  files: File[]; // Images files to be uploaded
  registerDate: string; // ISO date string
}

@Component({
  selector: 'add-house',
  imports: [FormsModule],
  templateUrl: './add-house.component.html',
  styleUrls: ['./add-house.component.css'],
})
export class AddHouseComponent {
  // Initialize the house metadata
  addedHouse: AddedHouse = {
    title: '',
    price: null,
    description: '',
    rating: null,
    location: '',
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
      location: this.addedHouse.location,
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
}
