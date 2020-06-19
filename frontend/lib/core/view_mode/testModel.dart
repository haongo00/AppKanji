import 'dart:convert';




import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

import '../../helper/generalinfor.dart';
import '../authenticationservice.dart';
import '../model/result.dart';
import '../model/testKanji.dart';

class TestModel extends ChangeNotifier {
  final AuthenticationService authenticationService;

  TestModel({this.authenticationService});

  List<TestKanji> _question = [];
  get question => _question;

  bool _isFetchingDataTestPage = false;
  get isFetchingDataTestPage => _isFetchingDataTestPage;
  setfetchingDataTestPage(bool val){
    if(_isFetchingDataTestPage != val) {
      _isFetchingDataTestPage = val;
      notifyListeners();
    }
  }

  List<int> _group = [0,0,0,0,0,0,0,0,0,0];

  get group => _group;

  setGroup(int index, int val){
    _group[index] = val;
    notifyListeners();
  }

  Map<int,String> answer = {
    0 : 'a',
    1 : 'b',
    2 : 'c',
    3 : 'd',
  };

  getAnswer(){
    String _answerToServer = '  ';
    List.generate(10, (index){
      _answerToServer += answer[_group[index]];
    });
    return _answerToServer.trim();
  }

  Future getDataTestPage(String _type_test) async {
    setfetchingDataTestPage(false);
    _question = await fetchDataTestPage(_type_test);
    setfetchingDataTestPage(true);
  }

  Future<List<TestKanji>> fetchDataTestPage(String _type_test ) async {
    print('log true');
    try {
      var res = await http.get(APIUrl + '/test?idUser=${authenticationService.id}&type_test=${_type_test}',

          headers: {'Content-Type': 'application/json'});
      if (res.statusCode == 200) //return res.body;
          {
        Map<String, dynamic> mapResponse = json.decode(res.body);
//        print(mapResponse.toString());

        if(mapResponse['data'] != null){
          final test = mapResponse['data'].cast<Map<String, dynamic>>();
          final listOfTest = await test.map<TestKanji>((json) {
            return TestKanji.fromJson(json);
          }).toList();
          return listOfTest;
        }
        else {
          return null;
        }

      } else {
        return null;
      }
    } catch (e) {
      print(' error ' + e.toString());
    }
//    notifyListeners();
  }

  ResultData _resultData = new ResultData();
  ResultData get resultData => _resultData;

  Future getResultData(String _answer) async {
    setfetchingResult(false);
    _resultData = await fetchResult(_answer);
    setfetchingResult(true);
  }

  bool _isFetchingResult = false;
  get isFetchingResult => _isFetchingResult;
  setfetchingResult(bool val){
    if(_isFetchingResult != val) {
      _isFetchingResult = val;
      notifyListeners();
    }
  }


  Future<ResultData> fetchResult(String _answer) async {
    try {
      var res = await http.get(APIUrl + '/getresult?choices=${_answer}&idUser=${authenticationService.id}',
          headers: {'Content-Type': 'application/json'});
      if (res.statusCode == 200) //return res.body;
          {
        Map<String, dynamic> mapResponse = json.decode(res.body);
        print(mapResponse.toString());

        if (mapResponse['data'] != null) {
          return ResultData.fromJson(mapResponse["data"][0]);
        } else {
          return null;
        }
      } else {
        return null;
      }
    } catch (e) {
      print(' error ' + e.toString());
    }
//    notifyListeners();
  }

}
