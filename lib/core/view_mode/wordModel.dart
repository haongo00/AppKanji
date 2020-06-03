import 'dart:convert';

import 'package:demoappkanji/core/model/Yomi.dart';
import 'package:demoappkanji/helper/generalinfor.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

import '../authenticationservice.dart';

class WordModel extends ChangeNotifier {
  final AuthenticationService authenticationService;

  WordModel({this.authenticationService});

  bool _likeword = false;

  bool get Likeword => _likeword;

  setLikeword(bool _val) {
    if (_val != _likeword) {
      _likeword = _val;
      notifyListeners();
    }
  }

  change() {
    setLikeword(!_likeword);
  }

  bool _isFetchYomi = false;

  bool get isFetchYomi => _isFetchYomi;

  setFetchYomi(bool _val) {
    _isFetchYomi = _val;
    notifyListeners();
  }

  List<Yomi> _kun = [];

  get kun => _kun;
List<Yomi> _on = [];

  get on => _on;

  String _message;

  get message => _message;

  void setMessage(String _s) {
    _message = _s;
    notifyListeners();
  }

  Future getDataKunYomi(int _idKanji) async {
    setFetchYomi(false);
    _kun = await dataKunYomi(_idKanji);
     _on = await dataOnYomi(_idKanji);
     setFetchYomi(true);
  }

  Future<List<Yomi>> dataKunYomi(int _idKanji) async {
    print(APIUrl + '/getkunyomiex?idKanji=${_idKanji}');
    try {
      var res = await http.get(APIUrl + '/getkunyomiex?idKanji=${_idKanji}',
          headers: {'Content-Type': 'application/json'});
      if (res.statusCode == 200) //return res.body;
      {
        Map<String, dynamic> mapResponse = json.decode(res.body);
//        print(mapResponse.toString());

        if (mapResponse['data'] != null) {
          final word = mapResponse['data'].cast<Map<String, dynamic>>();
          final listOfWord = await word.map<Yomi>((json) {
            return Yomi.fromJson(json);
          }).toList();
          return listOfWord;
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

  Future<List<Yomi>> dataOnYomi(int _idKanji) async {
    try {
      var res = await http.get(APIUrl + '/getonyomiex?idKanji=${_idKanji}',
          headers: {'Content-Type': 'application/json'});
      if (res.statusCode == 200) //return res.body;
      {
        Map<String, dynamic> mapResponse = json.decode(res.body);
//        print(mapResponse.toString());

        if (mapResponse['data'] != null) {
          final word = mapResponse['data'].cast<Map<String, dynamic>>();
          final listOfWord = await word.map<Yomi>((json) {
            return Yomi.fromJson(json);
          }).toList();
          return listOfWord;
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

  Future<bool> likeWord(int _idKanji) async {
    print('like');
    var data = {"idUser": authenticationService.id, "idKanji": _idKanji};

    try {
      var res = await http.post(APIUrl + '/addjvwords',
          body: json.encode(data),
          headers: {'Content-Type': 'application/json'});
      if (res.statusCode == 200) //return res.body;
      {
        Map<String, dynamic> mapResponse = json.decode(res.body);
//        print(mapResponse.toString());
//        setMessage('Thêm từ thành công !!!');
        return true;
      } else {
//        setMessage('Thêm từ thất bại !!!');

        return false;
      }
    } catch (e) {
      print(' error ' + e.toString());
    }
//    notifyListeners();
  }
  Future<bool> deleteLikeWord(int _idKanji) async {
    print('delete like');
    var data = {"idUser": authenticationService.id, "idKanji": _idKanji};
    print(data);

    try {
      var res = await http.post(APIUrl + '/delete',
          body: json.encode(data),
          headers: {'Content-Type': 'application/json'});
      if (res.statusCode == 200) //return res.body;
      {
        Map<String, dynamic> mapResponse = json.decode(res.body);
//        print(mapResponse.toString());
//        setMessage('Xóa từ thành công !!!');
        return true;
      } else {
//        setMessage('Xóa từ thất bại !!!');

        return false;
      }
    } catch (e) {
      print(' error ' + e.toString());
    }
//    notifyListeners();
  }


}
