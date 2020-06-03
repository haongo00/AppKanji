class Yomi {
  int idKanji;
  String vocab;
  String yomikata;
  String hanviet;
  String meaning;

  Yomi(
      {this.idKanji, this.vocab, this.yomikata, this.hanviet, this.meaning});

  factory Yomi.fromJson(Map<String, dynamic> json) => Yomi(
      idKanji: json['idKanji'],
      vocab: json['vocab'],
      yomikata: json['yomikata'],
      hanviet: json['hanviet'],
      meaning: json['meaning']);
}
