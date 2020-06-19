import 'dart:convert';

import 'package:http/http.dart' as http;

import 'package:flutter/foundation.dart';

import '../../helper/generalinfor.dart';
import '../authenticationservice.dart';
import '../model/searchWord.dart';

class LikeWordModel extends ChangeNotifier {

  final AuthenticationService authenticationService;

  LikeWordModel({this.authenticationService});

  bool _likeword = true;
  bool get Likeword => _likeword;
  setLikeword(bool _val) {
    if(_val != _likeword) {
      _likeword = _val;
      notifyListeners();
    }

  }
  change(){
    setLikeword(!_likeword);
  }

  Future<List<SearchWord>> likeWord() async {
    try {
      var res = await http.get(APIUrl + '/getfvwords?idUser=${authenticationService.id}',

          headers: {'Content-Type': 'application/json'});
      if (res.statusCode == 200) //return res.body;
          {
        Map<String, dynamic> mapResponse = json.decode(res.body);
//        print(mapResponse.toString());

        if(mapResponse['data'] != null){
          final word = mapResponse['data'].cast<Map<String, dynamic>>();
          final listOfWord = await word.map<SearchWord>((json) {
            return SearchWord.fromJson(json);
          }).toList();
          return listOfWord;
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

}