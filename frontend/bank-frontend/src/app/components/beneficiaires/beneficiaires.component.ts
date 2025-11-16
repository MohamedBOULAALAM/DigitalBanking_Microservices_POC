import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-beneficiaires',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './beneficiaires.component.html',
  styleUrl: './beneficiaires.component.css'
})
export class BeneficiairesComponent implements OnInit {
  beneficiaires: any[] = [];
  beneficiaire: any = {
    nom: '',
    prenom: '',
    rib: '',
    type: 'PHYSIQUE'
  };
  showForm = false;
  editingId: number | null = null;

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.loadBeneficiaires();
  }

  loadBeneficiaires() {
    this.apiService.getBeneficiaires().subscribe({
      next: (data) => this.beneficiaires = data,
      error: (err) => console.error('Erreur lors du chargement:', err)
    });
  }

  openForm(beneficiaire?: any) {
    if (beneficiaire) {
      this.beneficiaire = { ...beneficiaire };
      this.editingId = beneficiaire.id;
    } else {
      this.beneficiaire = { nom: '', prenom: '', rib: '', type: 'PHYSIQUE' };
      this.editingId = null;
    }
    this.showForm = true;
  }

  closeForm() {
    this.showForm = false;
    this.editingId = null;
  }

  saveBeneficiaire() {
    if (this.editingId) {
      this.apiService.updateBeneficiaire(this.editingId, this.beneficiaire).subscribe({
        next: () => {
          this.loadBeneficiaires();
          this.closeForm();
        },
        error: (err) => console.error('Erreur lors de la mise à jour:', err)
      });
    } else {
      this.apiService.createBeneficiaire(this.beneficiaire).subscribe({
        next: () => {
          this.loadBeneficiaires();
          this.closeForm();
        },
        error: (err) => console.error('Erreur lors de la création:', err)
      });
    }
  }

  deleteBeneficiaire(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce bénéficiaire ?')) {
      this.apiService.deleteBeneficiaire(id).subscribe({
        next: () => this.loadBeneficiaires(),
        error: (err) => console.error('Erreur lors de la suppression:', err)
      });
    }
  }
}

