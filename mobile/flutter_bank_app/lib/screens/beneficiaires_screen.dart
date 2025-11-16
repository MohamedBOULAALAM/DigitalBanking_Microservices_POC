import 'package:flutter/material.dart';
import '../services/api_service.dart';
import '../models/beneficiaire.dart';

class BeneficiairesScreen extends StatefulWidget {
  const BeneficiairesScreen({super.key});

  @override
  State<BeneficiairesScreen> createState() => _BeneficiairesScreenState();
}

class _BeneficiairesScreenState extends State<BeneficiairesScreen> {
  final ApiService _apiService = ApiService();
  List<Beneficiaire> _beneficiaires = [];
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    _loadBeneficiaires();
  }

  Future<void> _loadBeneficiaires() async {
    setState(() => _isLoading = true);
    try {
      final beneficiaires = await _apiService.getBeneficiaires();
      setState(() {
        _beneficiaires = beneficiaires;
        _isLoading = false;
      });
    } catch (e) {
      setState(() => _isLoading = false);
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Erreur: $e')),
        );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _isLoading
          ? const Center(child: CircularProgressIndicator())
          : _beneficiaires.isEmpty
              ? const Center(child: Text('Aucun bénéficiaire'))
              : ListView.builder(
                  itemCount: _beneficiaires.length,
                  itemBuilder: (context, index) {
                    final beneficiaire = _beneficiaires[index];
                    return Card(
                      margin: const EdgeInsets.all(8),
                      child: ListTile(
                        title: Text('${beneficiaire.nom} ${beneficiaire.prenom}'),
                        subtitle: Text('RIB: ${beneficiaire.rib}\nType: ${beneficiaire.type}'),
                        trailing: IconButton(
                          icon: const Icon(Icons.delete),
                          onPressed: () => _deleteBeneficiaire(beneficiaire.id),
                        ),
                      ),
                    );
                  },
                ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => _showAddBeneficiaireDialog(),
        child: const Icon(Icons.add),
      ),
    );
  }

  Future<void> _deleteBeneficiaire(int id) async {
    try {
      await _apiService.deleteBeneficiaire(id);
      _loadBeneficiaires();
    } catch (e) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Erreur: $e')),
        );
      }
    }
  }

  void _showAddBeneficiaireDialog() {
    // Implémentation simplifiée - à compléter
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Nouveau Bénéficiaire'),
        content: const Text('Fonctionnalité à implémenter'),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Fermer'),
          ),
        ],
      ),
    );
  }
}

