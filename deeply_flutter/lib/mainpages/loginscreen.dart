import 'dart:convert';

import 'package:deeply_flutter/mainpages/homescreen.dart';
import 'package:deeply_flutter/mainpages/registerscreen.dart';
import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:get/get_connect/http/src/response/response.dart';
import 'package:persistent_bottom_nav_bar/persistent_tab_view.dart';

import '../mainpage.dart';

import 'package:http/http.dart' as http;

import '../models/auth.dart';
import '../models/login.dart';

// var url = Uri.parse(
//   'https://localhost:9000/auth/login',
// );
// var response = await http.get(url);
// print('Response status: ${response.statusCode}');
// print('Response body: ${response.body}');

class LoginApp extends StatefulWidget {
  const LoginApp({Key? key}) : super(key: key);

  @override
  State<LoginApp> createState() => _LoginAppState();
}



class _LoginAppState extends State<LoginApp> {

  static final storage = FlutterSecureStorage(); // FlutterSecureStorage를 storage로 저장
  dynamic userInfo = ''; // storage에 있는 유저 정보를 저장


  //flutter_secure_storage 사용을 위한 초기화 작업
  @override
  void initState() {
    super.initState();

    // 비동기로 flutter secure storage 정보를 불러오는 작업
    WidgetsBinding.instance.addPostFrameCallback((_) {
      _asyncMethod();
    });
  }

  _asyncMethod() async {
    // read 함수로 key값에 맞는 정보를 불러오고 데이터타입은 String 타입
    // 데이터가 없을때는 null을 반환
    userInfo = await storage.read(key: 'login');

    // user의 정보가 있다면 로그인 후 들어가는 첫 페이지로 넘어가게 합니다.
    if (userInfo != null) {
      Navigator.pushNamed(context, '/main');
    } else {
      print('로그인이 필요합니다');
    }
  }

// 로그인 버튼 누르면 실행
  loginAction(email, password) async {
    try {
      var url = Uri.parse('http://localhost:9000/auth/signin',);
      var data = {"email": "local-user@mail.com", "password": "1111"};
      var param = {'email': '$email', 'password': '$password'};

      var response = await http.post(
          url,
          body: json.encode(param),
          headers: {"Content-Type": "application/json"});
      print(response.statusCode);
      print(response.body);


      if (response.statusCode == 200) {
        final jsonBody = json.decode(response.body).toString();
        // 직렬화를 이용하여 데이터를 입출력하기 위해 model.dart에 Login 정의 참고
        var val = jsonEncode(Login('$email', '$password', '$jsonBody'));
        await storage.write(key: 'login', value: val,);

        print('접속 성공!');
        return true;


      } else {
        print('error');
        return false;
      }
    } catch (e) {
      print('//gad');
      return false;
    }
  }

  void _showDialog(String text, String contents) {
    showDialog(
      context: context,
      barrierDismissible: false,
      builder: (BuildContext context) {
        return AlertDialog(
          shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8.0)
          ),
          title: new Text(text),
          content: SingleChildScrollView(child: new Text(contents)),
          actions: <Widget>[
            new TextButton(
              child: new Text("Close"),
              onPressed: () {
                Navigator.pop(context);
              },
            ),
          ],
        );
      },
    );
  }

  // void _callAPI() async {
  //   var url = Uri.parse('http://localhost:9000/auth/signin',);
  //   var data = {"email": "local-user@mail.com", "password": "1111"};
  //   var response = await http.post(
  //       url,
  //       body: json.encode(data),
  //       headers: {"Content-Type": "application/json"});
  //
  //   if (response.statusCode == 200) { // 로그인을 성공하면
  //     ResponseVO daf = ResponseVO.fromJSON(jsonDecode(response.body));
  //     String token = daf.accessToken;
  //     print(token); // response의 token키에 담긴 값을 token 변수에 담아서
  //   }
  // }


  var email = TextEditingController(); // id 입력 저장
  var password = TextEditingController(); // pw 입력 저장


  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('로그인'),
        ),
        body: Column(
          children: [
            // 아이디 입력 영역
            TextField(
              controller: email,
              decoration: InputDecoration(
                labelText: 'Username',
              ),
            ),
            // 비밀번호 입력 영역
            TextField(
              controller: password,
              decoration: InputDecoration(
                labelText: 'Password',
              ),
            ),
            // 로그인 버튼
            ElevatedButton(
              onPressed: () async {
                if (await loginAction(email.text, password.text) ==
                    true) {
                  print('로그인 성공');
                  Navigator.pushNamed(
                      context, '/service'); // 로그인 이후 서비스 화면으로 이동
                } else {
                  print('로그인 실패');
                }
              },
              child: Text('로그인 하기'),
            ),

            ElevatedButton(
              onPressed: () async {
                // Navigator.pushNamed(
                //     context, '/signup'); // 로그인 이후

                // 라우팅
                PersistentNavBarNavigator.pushNewScreen(
                  context,
                  screen: RegisterApp(),
                  withNavBar: true, // OPTIONAL VALUE. True by default.
                  pageTransitionAnimation: PageTransitionAnimation.cupertino,
                );


              },




              child: Text('회원가입'),
            ),

          ],
        )
    );
  }

}




      // body: SingleChildScrollView(
        // height: 150.0,
        // width: 190.0,
        // padding: EdgeInsets.only(top: 40),
        // decoration: BoxDecoration(
        //   borderRadius: BorderRadius.circular(200),
        // ),
        // child: Column(
        //   children: <Widget>[
        //     Padding(
        //       padding: const EdgeInsets.only(top: 60.0),
        //       child: Center(
        //         child: Container(
        //             width: 200,
        //             height: 150,
        //             /*decoration: BoxDecoration(
        //                 color: Colors.red,
        //                 borderRadius: BorderRadius.circular(50.0)),*/
        //             child: Image.asset('asset/image/deeply.png')),
        //       ),
        //     ),
        //
        //
        //     Padding(
        //       //padding: const EdgeInsets.only(left:15.0,right: 15.0,top:0,bottom: 0),
        //       padding: EdgeInsets.symmetric(horizontal: 15),
        //       child: TextField(
        //         decoration: InputDecoration(
        //             border: OutlineInputBorder(),
        //             labelText: 'Email',
        //             hintText: 'Enter valid email id as abc@gmail.com'),
        //       ),
        //     ),
        //
        //
        //
        //     Padding(
        //       padding: const EdgeInsets.only(
        //           left: 15.0, right: 15.0, top: 15, bottom: 0),
        //       //padding: EdgeInsets.symmetric(horizontal: 15),
        //       child: TextField(
        //         obscureText: true,
        //         decoration: InputDecoration(
        //             border: OutlineInputBorder(),
        //             labelText: 'Password',
        //             hintText: 'Enter secure password'),
        //       ),
        //     ),
        //     TextButton(
        //       onPressed: () {
        //         //TODO FORGOT PASSWORD SCREEN GOES HERE
        //       },
        //       child: Text(
        //         'Forgot Password',
        //         style: TextStyle(color: Colors.blue, fontSize: 15),
        //       ),
        //     ),
        //     Container(
        //       height: 50,
        //       width: 250,
        //       decoration: BoxDecoration(
        //           color: Colors.blue, borderRadius: BorderRadius.circular(20)),
        //       child: TextButton(
        //         onPressed: () {
        //           _showDialog("로그인 성공", "성공");
        //           // Navigator.push(
        //           //     context, MaterialPageRoute(builder: (_) => MainPage()));
        //         },
        //         child: Text(
        //           'Login',
        //           style: TextStyle(color: Colors.white, fontSize: 25),
        //         ),
        //       ),
        //     ),
        //     Container(
        //         height: 50,
        //         width: 250,
        //         decoration: BoxDecoration(
        //             color: Colors.red, borderRadius: BorderRadius.circular(20)),
        //         child: ElevatedButton(
        //           onPressed: _callAPI,
        //           child: const Text('Call API'),
        //         )),
        //     SizedBox(
        //       height: 130,
        //     ),
        //     Text('New User? Create Account')
        //   ],


