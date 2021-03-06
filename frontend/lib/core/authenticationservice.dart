import 'package:flutter/cupertino.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AuthenticationService extends ChangeNotifier {
  SharedPreferences _prefs;
  int _id;
  String _accountName;

  AuthenticationService() {
    reload();
  }


  Future<bool> _setInt(String key, int val) async {
    return await _prefs.setInt(key, val);
  }

  int _getInt(String key) {
    return _prefs.getInt(key);
  }

  Future<bool> _setString(String key, String val) async {
    return await _prefs.setString(key, val);
  }

  String _getString(String key) {
    return _prefs.getString(key);
  }

  get id {
    return _id;
  }

  void setId(int id) {
    if (id != _id) {
      _id = id;
      this._setInt("id", _id);
      notifyListeners();
    }
  }

  String get accountName {
    return _accountName;
  }

  void setAccountName(String accountName) {
    if (accountName != _accountName) {
      _accountName = accountName;
      this._setString("accountName", _accountName);
      notifyListeners();
    }
  }


  void reload() async {
    if (this._prefs == null) {
      _prefs = await SharedPreferences.getInstance();
    }
    _id = this._getInt("id") ?? null;
    _accountName = this._getString("accountName") ?? null;

  }

  get isLogined => _id != null ? true : false;





}