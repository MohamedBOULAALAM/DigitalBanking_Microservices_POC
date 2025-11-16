import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

const API_URL = 'http://localhost:8008/api';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}

  private handleError = (error: HttpErrorResponse) => {
    let errorMessage = 'Une erreur est survenue';
    if (error.error instanceof ErrorEvent) {
      // Erreur côté client
      errorMessage = `Erreur: ${error.error.message}`;
    } else {
      // Erreur côté serveur
      errorMessage = `Code: ${error.status}\nMessage: ${error.message}`;
      if (error.status === 0) {
        errorMessage = 'Impossible de se connecter au serveur. Vérifiez que le Gateway Service est démarré sur le port 8008.';
      }
    }
    console.error('Erreur HTTP:', error);
    return throwError(() => new Error(errorMessage));
  }

  // Bénéficiaires
  getBeneficiaires(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/beneficiaires`, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  getBeneficiaire(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/beneficiaires/${id}`, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  createBeneficiaire(beneficiaire: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/beneficiaires`, beneficiaire, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  updateBeneficiaire(id: number, beneficiaire: any): Observable<any> {
    return this.http.put<any>(`${API_URL}/beneficiaires/${id}`, beneficiaire, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  deleteBeneficiaire(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/beneficiaires/${id}`, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  // Virements
  getVirements(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/virements`, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  getVirement(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/virements/${id}`, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  createVirement(virement: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/virements`, virement, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  deleteVirement(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/virements/${id}`, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  getVirementsByBeneficiaire(beneficiaireId: number): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/virements/beneficiaire/${beneficiaireId}`, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  // Chatbot
  chat(message: string): Observable<any> {
    return this.http.post<any>(`${API_URL}/chatbot/chat`, { message }, this.httpOptions)
      .pipe(catchError(this.handleError));
  }
}

