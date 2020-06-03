class SearchWord {
  int idKanji;
  String kanji;
  String kunyomi;
  String onyomi;
  String hanviet;
  String set;
  String strokes;
  String meaning;
  String jlpt;
  bool status;

  SearchWord(
      {this.idKanji,
      this.kanji,
      this.kunyomi,
      this.onyomi,
      this.hanviet,
      this.status,
      this.strokes,
      this.set,
      this.meaning,
      this.jlpt});

  factory SearchWord.fromJson(Map<String, dynamic> json) => SearchWord(
      idKanji: json['idKanji'],
      kanji: json['kanji'],
      kunyomi: json['kunyomi'],
      onyomi: json['onyomi'],
      hanviet: json['hanviet'],
      set: json['set'],
      status: json['status'],
      strokes: json['strokes'],
      meaning: json['meaning'],
      jlpt: json['jlpt']);
}
