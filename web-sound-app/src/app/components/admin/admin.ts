import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';


@Component({
  selector: 'app-admin',
  standalone: true, //  necesario si usas componentes standalone
  imports: [CommonModule],
  templateUrl: './admin.html',
  styleUrls: ['./admin.css'] //  debe ser "styleUrls" (plural)
})
export class Admin {

}
