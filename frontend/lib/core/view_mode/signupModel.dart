

import 'package:provider/provider.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/cupertino.dart';

import '../../helper/generalinfor.dart';
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


      if(mapResponse["idUser"] != 0){
        print(mapResponse.toString());
        print('dhjcbjh');
        authenticationService.setId(mapResponse["idUser"]);
        print(mapResponse['userName']);
        authenticationService.setAccountName(mapResponse["userName"]);
//print(authenticationService.id.toString() + 'dshjsjh');
        _message = 'Đăng kí thành công';
        return true;
      }
      else {
        _message = mapResponse["response"];
        return false;
      }


    } else {
      return null;
    }

//    notifyListeners();
  }

  Future<bool> profile() async {

    var res = await http.get(APIUrl + '/getuserinfo?idUser=${authenticationService.id}',

        headers: {'Content-Type': 'application/json'});

    if (res.statusCode == 200) //return res.body;
        {
      Map<String, dynamic> mapResponse = json.decode(res.body);


      if(mapResponse["idUser"] != 0){
        print(mapResponse.toString());
        authenticationService.setAccountName(mapResponse["userName"]);
        return true;
      }
    } else {
      return null;
    }

//    notifyListeners();
  }

}