import { Routes } from '@angular/router';
import { Home } from './components/home/home'; // Importante importar el componente desde su ruta
import { Admin } from './components/admin/admin';
import { LogIn } from './components/log-in/log-in';

export const routes: Routes = [
    {path: 'home-page', component:Home},  // En este caso home-page sería el url
    {path: 'admin', component:Admin},
    {path: 'login-page', component: LogIn}
];
