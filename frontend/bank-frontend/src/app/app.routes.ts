import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: '/beneficiaires', pathMatch: 'full' },
  { path: 'beneficiaires', loadComponent: () => import('./components/beneficiaires/beneficiaires.component').then(m => m.BeneficiairesComponent) },
  { path: 'virements', loadComponent: () => import('./components/virements/virements.component').then(m => m.VirementsComponent) }
];
