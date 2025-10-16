import { Routes } from '@angular/router';
import { Home } from './components/home/home'; // Importante importar el componente desde su ruta

export const routes: Routes = [
    {path: 'home-page', component:Home}  // En este caso home-page sería el url
];
