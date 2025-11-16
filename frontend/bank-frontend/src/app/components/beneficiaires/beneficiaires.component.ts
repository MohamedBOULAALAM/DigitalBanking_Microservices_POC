import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
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
  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.loadBeneficiaires();
  }

  loadBeneficiaires() {
    this.isLoading = true;
    this.apiService.getBeneficiaires().subscribe({
      next: (data) => {
        this.beneficiaires = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement:', err);
        this.errorMessage = 'Erreur lors du chargement des bénéficiaires';
        this.isLoading = false;
      }
    });
  }

  openForm(beneficiaire?: any) {
    this.errorMessage = '';
    if (beneficiaire) {
      this.beneficiaire = { ...beneficiaire };
      this.editingId = beneficiaire.id;
    } else {
      this.beneficiaire = { nom: '', prenom: '', rib: '', type: 'PHYSIQUE' };
      this.editingId = null;
    }
    this.showForm = true;
  }

  filterRibInput() {
    if (this.beneficiaire.rib) {
      // Ne garder que les chiffres et limiter à 24 caractères
      this.beneficiaire.rib = this.beneficiaire.rib.replace(/[^0-9]/g, '').substring(0, 24);
    }
  }

  closeForm() {
    this.showForm = false;
    this.editingId = null;
    this.errorMessage = '';
  }

  saveBeneficiaire(form: NgForm) {
    if (!form.valid) {
      this.errorMessage = 'Veuillez remplir tous les champs obligatoires correctement';
      return;
    }

    // S'assurer que le RIB contient exactement 24 chiffres
    if (!this.beneficiaire.rib || this.beneficiaire.rib.length !== 24) {
      this.errorMessage = `Le RIB doit contenir exactement 24 chiffres (actuellement: ${this.beneficiaire.rib?.length || 0})`;
      return;
    }

    // Vérifier que le RIB ne contient que des chiffres
    if (!/^[0-9]{24}$/.test(this.beneficiaire.rib)) {
      this.errorMessage = 'Le RIB ne doit contenir que des chiffres';
      return;
    }

    this.errorMessage = '';
    this.isLoading = true;

    if (this.editingId) {
      this.apiService.updateBeneficiaire(this.editingId, this.beneficiaire).subscribe({
        next: () => {
          this.loadBeneficiaires();
          this.closeForm();
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Erreur lors de la mise à jour:', err);
          this.errorMessage = this.extractErrorMessage(err);
          this.isLoading = false;
        }
      });
    } else {
      this.apiService.createBeneficiaire(this.beneficiaire).subscribe({
        next: () => {
          this.loadBeneficiaires();
          this.closeForm();
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Erreur lors de la création:', err);
          this.errorMessage = this.extractErrorMessage(err);
          this.isLoading = false;
        }
      });
    }
  }

  private extractErrorMessage(err: any): string {
    if (err.error) {
      // Erreur de validation avec structure errors
      if (err.error.errors && typeof err.error.errors === 'object') {
        const errorMessages = Object.values(err.error.errors).join(', ');
        return errorMessages || err.error.message || 'Erreur de validation';
      }
      // Message d'erreur simple
      if (err.error.message) {
        return err.error.message;
      }
      // Erreur sous forme de string
      if (typeof err.error === 'string') {
        return err.error;
      }
    }
    return err.message || 'Erreur lors de l\'opération';
  }

  deleteBeneficiaire(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce bénéficiaire ?')) {
      this.isLoading = true;
      this.apiService.deleteBeneficiaire(id).subscribe({
        next: () => {
          this.loadBeneficiaires();
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Erreur lors de la suppression:', err);
          this.errorMessage = 'Erreur lors de la suppression du bénéficiaire';
          this.isLoading = false;
        }
      });
    }
  }
}

