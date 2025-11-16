import 'package:flutter/material.dart';
import '../services/api_service.dart';
import '../models/virement.dart';

class VirementsScreen extends StatefulWidget {
  const VirementsScreen({super.key});

  @override
  State<VirementsScreen> createState() => _VirementsScreenState();
}

class _VirementsScreenState extends State<VirementsScreen> {
  final ApiService _apiService = ApiService();
  List<Virement> _virements = [];
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    _loadVirements();
  }

  Future<void> _loadVirements() async {
    setState(() => _isLoading = true);
    try {
      final virements = await _apiService.getVirements();
      setState(() {
        _virements = virements;
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
          : _virements.isEmpty
              ? const Center(child: Text('Aucun virement'))
              : ListView.builder(
                  itemCount: _virements.length,
                  itemBuilder: (context, index) {
                    final virement = _virements[index];
                    return Card(
                      margin: const EdgeInsets.all(8),
                      child: ListTile(
                        title: Text('Montant: ${virement.montant} €'),
                        subtitle: Text(
                          'RIB Source: ${virement.ribSource}\n'
                          'Date: ${virement.dateVirement}\n'
                          'Type: ${virement.type}',
                        ),
                      ),
                    );
                  },
                ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => _showAddVirementDialog(),
        child: const Icon(Icons.add),
      ),
    );
  }

  void _showAddVirementDialog() {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Nouveau Virement'),
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

