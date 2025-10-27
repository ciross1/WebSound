import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('web-sound-app');

  // Inyectamos Router usando la función inject()
  private router = inject(Router);

  // Método que devuelve true si estamos en /admin
  isAdminRoute(): boolean {
    const currentUrl = this.router.url ?? '';  //this.router es el servicio de Angular Router que te dice en qué ruta estás.
    // this.router.url devuelve la URL actual como string, por ejemplo:

    return currentUrl.startsWith('/admin'); //  agrega la barra inicial
  }
}
