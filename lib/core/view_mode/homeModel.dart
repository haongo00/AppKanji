import 'dart:convert';
import 'package:demoappkanji/core/model/searchWord.dart';
import 'package:http/http.dart' as http;
import 'package:demoappkanji/helper/generalinfor.dart';
import 'package:flutter/foundation.dart';

import '../authenticationservice.dart';

class HomeModel extends ChangeNotifier {

  final AuthenticationService authenticationService;

  HomeModel({this.authenticationService});


  bool _likeword = false;
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



  bool _checkData = false;
  bool get checkData => _checkData;
  void setCheckData(bool _val) {
    _checkData = _val;
    notifyListeners();
  }
  List<SearchWord> _searchWord = new List<SearchWord>();
  get wordLength => _searchWord.length;
  get word => _searchWord;


  Future<List<SearchWord>> search(String _s) async {
    var data = {
      "key":_s,
      "idUser": authenticationService.id.toString()
    };
    print(data);
    try {
      var res = await http.post(APIUrl + '/search',

          body: json.encode(data),
          headers: {'Content-Type': 'application/json'});

      if (res.statusCode == 200) //return res.body;
          {
        Map<String, dynamic> mapResponse = json.decode(res.body);
        print(mapResponse.toString());

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

  Future getDataWord(String _s) async {
    setCheckData(false);
//    print(_checkData);
    _searchWord = await search(_s);
    setCheckData(true);
//    print(_checkData);

  }

}