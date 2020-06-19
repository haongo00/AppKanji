class ResultData {
  List<dynamic> results;
  int correct_answers;

  ResultData({this.results, this.correct_answers});

  factory ResultData.fromJson(Map<String, dynamic> json) => ResultData(
    results: (json['results']).map((e)=>e.toString()).toList(),
    correct_answers: json['correct_answers']
  );

}