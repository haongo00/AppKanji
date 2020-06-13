import 'package:demoappkanji/core/view_mode/signupModel.dart';
import 'package:demoappkanji/helper/generalinfor.dart';
import 'package:demoappkanji/ui/HomePage.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:provider/provider.dart';

class SignUpPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return new _SignUpPage();
  }
}

class _SignUpPage extends State<SignUpPage> {
  Map signupInfor = new Map();
  String _name_hint = 'Họ và Tên *',
      _email_hint = 'Tên đăng nhập * ',
      _password_hint = 'Mật khẩu *',
      _password_confimation_hint = 'Xác nhận mật khẩu *';

  GlobalKey<FormState> _key1 = new GlobalKey();
  bool _validate = false;
  TextEditingController _email = new TextEditingController();
  TextEditingController _name = new TextEditingController();
  TextEditingController _password = new TextEditingController();
  TextEditingController _password_confirm = new TextEditingController();

  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();
  void showInSnackBar(String value) {
    _scaffoldKey.currentState.showSnackBar(
      SnackBar(
        content: new Text(value, textAlign: TextAlign.start),
        duration: Duration(seconds: 1),
      ),
    );
  }

  String validatePass(String value) {
    RegExp regExp = new RegExp(r'(^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,32}$)');
    return value.length == 0
        ? "Trường này không được để trống"
        : !regExp.hasMatch(value)
        ? "Mật khẩu không đúng định dạng"
        : (value == _password_confirm.text) ? null : "Mật khẩu không trùng khớp";
  }

  String validateName(String value) {
    String patttern = r'(^[a-zA-Z ]*$)';
    RegExp regExp = new RegExp(patttern);
    if (value.length == 0) {
      return "Trường này không được để trống";
    }
    return null;
  }

  String validateMobile(String value) {

    String patttern = r'(^[0-9]*$)';
    RegExp regExp = new RegExp(patttern);
    if (value.length == 0) {
      return "Trường này không được để trống";
    }else if (!regExp.hasMatch(value)) {
      return " Không đúng định dạng";
    } else {
      return null;
    }
  }

  String validateEmail(String value) {
    String pattern =
        r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$';
    RegExp regExp = new RegExp(pattern);
    if (value.length == 0) {
      return "Trường này không được để trống";
    }
      return null;
  }

  _saveToServer() {
    if (_key1.currentState.validate()) {
      // No any error in validation
      _key1.currentState.save();
    } else {
      // validation error
      setState(() {
        _validate = true;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
//        leading: buildPreviousButton(),
        backgroundColor: colorApp,
        title: Text(
          'Đăng ký',
        ),

        centerTitle: true,
      ),
      body: new SingleChildScrollView(
        //physics: const NeverScrollableScrollPhysics(),
        child: Form(
          key: _key1,
          autovalidate: _validate,
          child: Container(
            //child: new Padding(padding: EdgeInsets.all(30.0),
            child: new Column(
              children: <Widget>[
                // _Chooserole(),
                new Container(
                  child: new Padding(
                    padding: EdgeInsets.all(10.0),
                    child: Text(
                      'Vui lòng điền đầy đủ thông tin vào những mục (*)',
                      textAlign: TextAlign.center,
                      style: TextStyle(fontSize: 15.0, color: Colors.red),
                    ),
                  ),
                ),
                new Padding(
                  padding: EdgeInsets.only(left: 18.0, right: 18.0),
                  child: Column(
                    children: <Widget>[
                      _TextFieldsName(_name_hint),
                      _TextFieldsEmail(_email_hint),
                      _TextFieldsPass(_password_hint, _password),
                      _TextFieldsPassConfirmation(_password_confimation_hint, _password_confirm),
                      SizedBox(
                        height: 5,
                      ),
                      Center(
//                        padding: EdgeInsets.all(0.0),
                        child: Text(
                          '*Tên tài khoản bao gồm số và chữ, "." hoặc "_".',
                          style: TextStyle(
                              fontSize: 14, fontStyle: FontStyle.italic),
                        ),
                      ),
                      Center(
//                        padding: EdgeInsets.all(10.0),
                        child: Text(
                          '*Mật khẩu ít nhất 8 ký tự (bao gồm chữ và số).',
                          style: TextStyle(
                              fontSize: 14, fontStyle: FontStyle.italic),
                        ),
                      ),

                      SizedBox(height: 20),
                      Consumer<SignUpModel>(builder: (_, model, __) {
                        return Padding(
                          padding: EdgeInsets.all(15.0),
                          child: Center(
                            child: RaisedButton(
                              onPressed: () async {
                                _saveToServer();
                                signupInfor["userName"] = _name.text;
                                signupInfor["accountName"] = _email.text;
                                signupInfor["pass"] = _password.text;
//                                signupInfor["password_confirmation"] =
//                                    _password_confirm.text;
                                var success = await model.signup(signupInfor);
                                if (success) {
                                  Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                        builder: (context) => HomePage(),
                                      ));
                                }
                                else {
                                  var _message = await model.message;
                                  showInSnackBar(_message);
                                }
                              },
                              color: colorApp,
                              child: new Padding(
                                padding: EdgeInsets.all(7.0),
                                child: Text(
                                  'Đăng ký',
                                  style: TextStyle(
                                      fontSize: 18,
                                      fontWeight: FontWeight.w400,
                                      color: Colors.white),
                                ),
                              ),
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(10.0),
                              ),
                            ),
                          ),
                        );
                      }),
                    ],
                  ),
                ),
              ],
            ),
            //),
          ),
        ),
      ),
    );
  }

  Widget _TextFieldsName(
      String _text,
      ) {
    return new Container(
      child: new TextFormField(
//          controller: _emailFilter,
//          autofocus: true,
          // textCapitalization: TextCapitalization.characters,
          style: TextStyle(fontSize: 20.0),
          decoration: new InputDecoration(
            hintText: _text,

            hintStyle: TextStyle(fontSize: 20.0),
            contentPadding: EdgeInsets.only(bottom: 5),
          ),
          keyboardType: TextInputType.text,
//                            maxLength: 10,
          validator: validateName,
          onSaved: (String val) {
            _name.text = val.trim();
          }),
    );
  }

  Widget _TextFieldsEmail(String _text) {
    return new Container(
      child: new TextFormField(
        //controller: _emailFilter,
//          autofocus: true,
          style: TextStyle(fontSize: 20.0),
          decoration: new InputDecoration(
            contentPadding: EdgeInsets.only(bottom: 5),
            labelText: _text,
            labelStyle: TextStyle(fontSize: 20.0),
            // hintStyle: TextStyle(fontSize: 20.0),
            // hintText: _text
          ),
          keyboardType: TextInputType.emailAddress,
//                            maxLength: 10,
          validator: validateEmail,
          onSaved: (String val) {
            _email.text = val.trim();
          }),
    );
  }

  Widget _TextFieldsPass(String _text, TextEditingController _pass) {
    return new Container(
      child: new TextFormField(
        //controller: _emailFilter,

//          autofocus: true,
          obscureText: true,
          style: TextStyle(fontSize: 20.0),
          decoration: new InputDecoration(
              contentPadding: EdgeInsets.only(bottom: 5),
              hintStyle: TextStyle(fontSize: 20.0),
              hintText: _text),
          keyboardType: TextInputType.visiblePassword,
//                            maxLength: 10,
          validator: validatePass,
          onSaved: (String val) {
            _password.text = val.trim();
          }),
    );
  }

  Widget _TextFieldsPassConfirmation(String _text, TextEditingController _pass) {
    return new Container(
      child: new TextFormField(
        controller: _password_confirm,

//          autofocus: true,
          obscureText: true,
          style: TextStyle(fontSize: 20.0),
          decoration: new InputDecoration(
              contentPadding: EdgeInsets.only(bottom: 5),
              hintStyle: TextStyle(fontSize: 20.0),
              hintText: _text),
          keyboardType: TextInputType.visiblePassword,
//                            maxLength: 10,
          validator: validatePass,
          onSaved: (String val) {
            _password_confirm.text = val.trim();
          }),
    );
  }
}