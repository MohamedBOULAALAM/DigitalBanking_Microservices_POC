import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-virements',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './virements.component.html',
  styleUrl: './virements.component.css'
})
export class VirementsComponent implements OnInit {
  virements: any[] = [];
  beneficiaires: any[] = [];
  virement: any = {
    beneficiaireId: null,
    ribSource: '',
    montant: null,
    description: '',
    type: 'NORMAL'
  };
  showForm = false;
  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.loadVirements();
    this.loadBeneficiaires();
  }

  loadVirements() {
    this.isLoading = true;
    this.apiService.getVirements().subscribe({
      next: (data) => {
        this.virements = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement:', err);
        this.errorMessage = 'Erreur lors du chargement des virements';
        this.isLoading = false;
      }
    });
  }

  loadBeneficiaires() {
    this.apiService.getBeneficiaires().subscribe({
      next: (data) => this.beneficiaires = data,
      error: (err) => {
        console.error('Erreur lors du chargement:', err);
        this.errorMessage = 'Erreur lors du chargement des bénéficiaires';
      }
    });
  }

  openForm() {
    this.errorMessage = '';
    this.virement = {
      beneficiaireId: null,
      ribSource: '',
      montant: null,
      description: '',
      type: 'NORMAL'
    };
    this.showForm = true;
  }

  filterRibSourceInput() {
    if (this.virement.ribSource) {
      // Ne garder que les chiffres et limiter à 24 caractères
      this.virement.ribSource = this.virement.ribSource.replace(/[^0-9]/g, '').substring(0, 24);
    }
  }

  closeForm() {
    this.showForm = false;
    this.errorMessage = '';
  }

  saveVirement(form: NgForm) {
    if (!form.valid) {
      this.errorMessage = 'Veuillez remplir tous les champs obligatoires';
      return;
    }

    // Validation du RIB source
    if (!this.virement.ribSource || this.virement.ribSource.length !== 24) {
      this.errorMessage = `Le RIB source doit contenir exactement 24 chiffres (actuellement: ${this.virement.ribSource?.length || 0})`;
      return;
    }

    if (!/^[0-9]{24}$/.test(this.virement.ribSource)) {
      this.errorMessage = 'Le RIB source ne doit contenir que des chiffres';
      return;
    }

    this.errorMessage = '';
    this.isLoading = true;

    this.apiService.createVirement(this.virement).subscribe({
      next: () => {
        this.loadVirements();
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

  deleteVirement(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce virement ?')) {
      this.isLoading = true;
      this.apiService.deleteVirement(id).subscribe({
        next: () => {
          this.loadVirements();
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Erreur lors de la suppression:', err);
          this.errorMessage = 'Erreur lors de la suppression du virement';
          this.isLoading = false;
        }
      });
    }
  }
}

