import 'dart:convert';
import 'dart:async';

import 'package:flutter/widgets.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

import '../models/http_exception.dart';

class Auth with ChangeNotifier {
  String? _token;
  DateTime? _expiryDate;
  String? _userId;
  Timer? _authTimer;
  String? _email;

  bool get isAuth {
    return token != null;
  }

  String? get token {
    if (_expiryDate != null &&
        _expiryDate!.isAfter(DateTime.now()) &&
        _token != null) {
      return _token;
    }
    return null;
  }

  String? get userId {
    return _userId;
  }

  String? get email {
    return _email;
  }

  Future<void> signup(String email, String password) async {
    final url = Uri.parse("http://localhost:9000/auth/signup");

    try {
      final response = await http.post(
        url,
        body: json.encode(
          {
            'email': email,
            'password': password,
            'returnSecureToken': true,
          },
        ),
      );

      final responseData = json.decode(response.body);
      if (responseData['error'] != null) {
        throw HttpException(responseData['error']['message']);
      }
      _token = responseData['accessToken'];
      _userId = responseData['userId'];

      // _expiryDate = DateTime.now().add(
      //   Duration(
      //     seconds: int.parse(
      //       responseData['expiresIn'],
      //     ),
      //   ),
      // );

      _email = responseData['email'];
      // _autoLogout();

      notifyListeners();

      final prefs = await SharedPreferences.getInstance();

      final userData = json.encode(
        {
          'token': _token,
          'userId': _userId,
          // 'expiryDate': _expiryDate!.toIso8601String(),
          'email': _email,
        },
      );
      prefs.setString('userData', userData);
    } catch (error) {
      throw error;
    }
  }

  Future<void> login(String email, String password) async {
    final url = Uri.parse("http://localhost:9000/auth/signup");

    try {
      final response = await http.post(
        url,
        body: json.encode(
          {
            'email': email,
            'password': password,
            'returnSecureToken': true,
          },
        ),
      );

      final responseData = json.decode(response.body);
      if (responseData['error'] != null) {
        throw HttpException(responseData['error']['message']);
      }
      _token = responseData['accessToken'];
      _userId = responseData['userId'];

      // _expiryDate = DateTime.now().add(
      //   Duration(
      //     seconds: int.parse(
      //       responseData['expiresIn'],
      //     ),
      //   ),
      // );

      _email = responseData['email'];
      // _autoLogout();

      notifyListeners();

      final prefs = await SharedPreferences.getInstance();

      final userData = json.encode(
        {
          'token': _token,
          'userId': _userId,
          // 'expiryDate': _expiryDate!.toIso8601String(),
          'email': _email,
        },
      );
      prefs.setString('userData', userData);
    } catch (error) {
      throw error;
    }
  }

  Future<bool> tryAutoLogin() async {
    final prefs = await SharedPreferences.getInstance();
    if (!prefs.containsKey('userData')) {
      return false;
    }
    // final extractedUserData =
    // json.decode(prefs.getString('userData')) as Map<String, Object>;

    // final expiryDate =
    // DateTime.parse(extractedUserData['expiryDate'] as String);

    // if (expiryDate.isBefore(DateTime.now())) {
    //   return false;
    // }
    // _token = extractedUserData['token'] as String;
    // _userId = extractedUserData['userId'] as String;
    // _expiryDate = expiryDate;
    // _email = extractedUserData['email'] as String;
    notifyListeners();
    // _autoLogout();
    return true;
  }

  Future<void> logout() async {
    _token = null;
    _userId = null;
    _expiryDate = null;
    _email = null;
    if (_authTimer != null) {
      _authTimer!.cancel();
      _authTimer = null;
    }
    notifyListeners();
    final prefs = await SharedPreferences.getInstance();
    // prefs.remove('userData');
    prefs.clear();
  }

//
//
// void _autoLogout() {
//   if (_authTimer != null) {
//     _authTimer!.cancel();
//   }
//   final timeToExpiry = _expiryDate!.difference(DateTime.now()).inSeconds;
//   _authTimer = Timer(Duration(seconds: timeToExpiry), logout);
// }
//

}
