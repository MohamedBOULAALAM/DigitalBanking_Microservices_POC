class Virement {
  final int id;
  final int beneficiaireId;
  final String ribSource;
  final double montant;
  final String? description;
  final String dateVirement;
  final String type;

  Virement({
    required this.id,
    required this.beneficiaireId,
    required this.ribSource,
    required this.montant,
    this.description,
    required this.dateVirement,
    required this.type,
  });

  factory Virement.fromJson(Map<String, dynamic> json) {
    return Virement(
      id: json['id'],
      beneficiaireId: json['beneficiaireId'],
      ribSource: json['ribSource'],
      montant: json['montant'].toDouble(),
      description: json['description'],
      dateVirement: json['dateVirement'],
      type: json['type'],
    );
  }
}

