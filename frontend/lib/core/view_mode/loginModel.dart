import 'dart:convert';
import 'package:http/http.dart' as http;

import 'package:flutter/foundation.dart';

import '../../helper/generalinfor.dart';
import '../authenticationservice.dart';


class LoginModel extends ChangeNotifier {

  final AuthenticationService authenticationService;

  LoginModel({this.authenticationService});
  bool _count = true;

  bool get count => _count;

  void setCount(bool val) {
    if (val != _count) {
      _count = val;
      notifyListeners();
    }
  }

  change() {
    setCount(!_count);
  }

  String _message;
  String get message => _message;
  void setMessage(String _val){
    _message = _val;
    notifyListeners();
  }

  Future<bool> login1(Map _map) async {
//    var data =  _map;
    _map["accountName"] = "kien.1807";
    _map["pass"] = '123456';
    var user  = 'accountName=${_map["accountName"]}&pass=${_map["pass"]}';
    print(user );

    try {
      var res = await http.get(APIUrl + '/login?' +user,

//          body: json.encode(data),
          headers: {'Content-Type': 'application/json'});

      if (res.statusCode == 200) //return res.body;
          {
        Map<String, dynamic> mapResponse = json.decode(res.body);
        print(mapResponse.toString());

          if(mapResponse["idUser"] != 0){
            authenticationService.setId(mapResponse["idUser"]);
            _message = 'Đăng nhập thành công';
            return true;
          }
          else {
            _message = 'Đăng nhập thất bại';
            return false;
          }


      } else {
        return null;
      }
    } catch (e) {
      print('login error ' + e.toString());
    }
    notifyListeners();
  }

}