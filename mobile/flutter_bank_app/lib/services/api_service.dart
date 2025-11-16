import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/beneficiaire.dart';
import '../models/virement.dart';

class ApiService {
  static const String baseUrl = 'http://localhost:8080/api';

  Future<List<Beneficiaire>> getBeneficiaires() async {
    final response = await http.get(Uri.parse('$baseUrl/beneficiaires'));
    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      return data.map((json) => Beneficiaire.fromJson(json)).toList();
    } else {
      throw Exception('Erreur lors du chargement des bénéficiaires');
    }
  }

  Future<void> deleteBeneficiaire(int id) async {
    final response = await http.delete(Uri.parse('$baseUrl/beneficiaires/$id'));
    if (response.statusCode != 204) {
      throw Exception('Erreur lors de la suppression');
    }
  }

  Future<List<Virement>> getVirements() async {
    final response = await http.get(Uri.parse('$baseUrl/virements'));
    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      return data.map((json) => Virement.fromJson(json)).toList();
    } else {
      throw Exception('Erreur lors du chargement des virements');
    }
  }
}

