import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
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

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.loadVirements();
    this.loadBeneficiaires();
  }

  loadVirements() {
    this.apiService.getVirements().subscribe({
      next: (data) => this.virements = data,
      error: (err) => console.error('Erreur lors du chargement:', err)
    });
  }

  loadBeneficiaires() {
    this.apiService.getBeneficiaires().subscribe({
      next: (data) => this.beneficiaires = data,
      error: (err) => console.error('Erreur lors du chargement:', err)
    });
  }

  openForm() {
    this.virement = {
      beneficiaireId: null,
      ribSource: '',
      montant: null,
      description: '',
      type: 'NORMAL'
    };
    this.showForm = true;
  }

  closeForm() {
    this.showForm = false;
  }

  saveVirement() {
    this.apiService.createVirement(this.virement).subscribe({
      next: () => {
        this.loadVirements();
        this.closeForm();
      },
      error: (err) => console.error('Erreur lors de la création:', err)
    });
  }

  deleteVirement(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce virement ?')) {
      this.apiService.deleteVirement(id).subscribe({
        next: () => this.loadVirements(),
        error: (err) => console.error('Erreur lors de la suppression:', err)
      });
    }
  }
}

