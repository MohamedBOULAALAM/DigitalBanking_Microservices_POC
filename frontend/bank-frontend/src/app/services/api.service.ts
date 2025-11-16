import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private http: HttpClient) {}

  // Bénéficiaires
  getBeneficiaires(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/beneficiaires`);
  }

  getBeneficiaire(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/beneficiaires/${id}`);
  }

  createBeneficiaire(beneficiaire: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/beneficiaires`, beneficiaire);
  }

  updateBeneficiaire(id: number, beneficiaire: any): Observable<any> {
    return this.http.put<any>(`${API_URL}/beneficiaires/${id}`, beneficiaire);
  }

  deleteBeneficiaire(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/beneficiaires/${id}`);
  }

  // Virements
  getVirements(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/virements`);
  }

  getVirement(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/virements/${id}`);
  }

  createVirement(virement: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/virements`, virement);
  }

  deleteVirement(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/virements/${id}`);
  }

  getVirementsByBeneficiaire(beneficiaireId: number): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/virements/beneficiaire/${beneficiaireId}`);
  }

  // Chatbot
  chat(message: string): Observable<any> {
    return this.http.post<any>(`${API_URL}/chatbot/chat`, { message });
  }
}

