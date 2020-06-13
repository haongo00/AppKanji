
import 'package:demoappkanji/helper/generalinfor.dart';
import 'package:provider/provider.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/cupertino.dart';

import '../authenticationservice.dart';


class SignUpModel extends ChangeNotifier{
  final AuthenticationService authenticationService;

  SignUpModel({this.authenticationService});

  String _message;
  String get message => _message;
  void setMessage(String _val){
    _message = _val;
    notifyListeners();
  }

  Future<bool> signup(Map _map) async {
    var data =  _map;
//    var data1 = {
//      "userName":"hao1",
//      "accountName": "01234567843",
//      "pass": "123456789"
//
//    };
    print(data);
//    print(data1);

      var res = await http.post(APIUrl + '/signup',

          body: json.encode(data),
          headers: {'Content-Type': 'application/json'});

      if (res.statusCode == 200) //return res.body;
          {
        Map<String, dynamic> mapResponse = json.decode(res.body);
        print(mapResponse.toString());

        if(mapResponse["idUser"] != 0){
          authenticationService.setId(mapResponse["idUser"]);
          _message = 'Đăng kí thành công';
          return true;
        }
        else {
          _message = 'Đăng kí thất bại';
          return false;
        }


      } else {
        return null;
      }

//    notifyListeners();
  }

}