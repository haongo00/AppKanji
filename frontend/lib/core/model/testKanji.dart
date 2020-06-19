class TestKanji {
  String question;
  String answer;
  Answerops answerops;

  TestKanji({this.question, this.answer, this.answerops});

  factory TestKanji.fromJson(Map<String, dynamic> json) => TestKanji(
        question: json['question'],
        answer: json['answer'],
        answerops: Answerops.fromJson(json['answerops']),
      );
}

class Answerops {
  String A_option;
  String B_option;
  String C_option;
  String D_option;

  Answerops({this.A_option, this.B_option, this.C_option, this.D_option});

  factory Answerops.fromJson(Map<String, dynamic> json) => Answerops(
        A_option: json['A_option'],
        B_option: json['B_option'],
        C_option: json['C_option'],
        D_option: json['D_option'],
      );
}
