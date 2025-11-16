class Beneficiaire {
  final int id;
  final String nom;
  final String prenom;
  final String rib;
  final String type;

  Beneficiaire({
    required this.id,
    required this.nom,
    required this.prenom,
    required this.rib,
    required this.type,
  });

  factory Beneficiaire.fromJson(Map<String, dynamic> json) {
    return Beneficiaire(
      id: json['id'],
      nom: json['nom'],
      prenom: json['prenom'],
      rib: json['rib'],
      type: json['type'],
    );
  }
}

