import 'dart:convert';

import 'package:deeply_flutter/mainpages/homescreen.dart';
import 'package:flutter/material.dart';

import '../mainpage.dart';

import 'package:http/http.dart' as http;

// var url = Uri.parse(
//   'https://localhost:9000/auth/login',
// );
// var response = await http.get(url);
// print('Response status: ${response.statusCode}');
// print('Response body: ${response.body}');

class RegisterApp extends StatefulWidget {
  const RegisterApp({Key? key}) : super(key: key);

  @override
  State<RegisterApp> createState() => _RegisterAppState();
}

class _RegisterAppState extends State<RegisterApp> {
  void _showDialog(String text, String contents) {
    showDialog(
      context: context,
      barrierDismissible: false,
      builder: (BuildContext context) {
        return AlertDialog(
          shape:
              RoundedRectangleBorder(borderRadius: BorderRadius.circular(8.0)),
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

  void _callAPI() async {
    var url = Uri.parse(
      'http://localhost:9000/auth/signup',
    );

    print(url);
    var data = {"email": "local-user2@mail.com", "password": "1111"};
    var jsonbody = json.encode(data);

    var response = await http.post(url,
        body: jsonbody, headers: {"Content-Type": "application/json"});
    print('${response}');
    print('${response.statusCode}');
    print('${response.body}');

    // print('Response status: ${response.statusCode}');
    // print('Response body: ${response.body}');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('회원가입'),
      ),
      body: SingleChildScrollView(
        // height: 150.0,
        // width: 190.0,
        // padding: EdgeInsets.only(top: 40),
        // decoration: BoxDecoration(
        //   borderRadius: BorderRadius.circular(200),
        // ),
        child: Column(
          children: <Widget>[

            Padding(
              padding: const EdgeInsets.only(top: 60.0),
              child: Center(
                child: Container(
                    width: 200,
                    height: 150,
                    /*decoration: BoxDecoration(
                        color: Colors.red,
                        borderRadius: BorderRadius.circular(50.0)),*/
                    child: Image.asset('asset/image/deeply.png')),
              ),
            ),


            Padding(
              //padding: const EdgeInsets.only(left:15.0,right: 15.0,top:0,bottom: 0),
              padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextField(
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Email',
                    hintText: 'Enter valid email id as abc@gmail.com'),
              ),
            ),

            Padding(
              padding: const EdgeInsets.only(
                  left: 15.0, right: 15.0, top: 15, bottom: 0),
              //padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextField(
                obscureText: true,
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Password',
                    hintText: 'Enter secure password'),
              ),
            ),

            TextButton(
              onPressed: () {
                //TODO FORGOT PASSWORD SCREEN GOES HERE
              },
              child: Text(
                'Forgot Password',
                style: TextStyle(color: Colors.blue, fontSize: 15),
              ),
            ),

            Container(
              height: 50,
              width: 250,
              decoration: BoxDecoration(
                  color: Colors.blue, borderRadius: BorderRadius.circular(20)),
              child: TextButton(
                onPressed: () {
                  _showDialog("회원가입성공", "성공");
                  // Navigator.push(
                  //     context, MaterialPageRoute(builder: (_) => MainPage()));
                },
                child: Text(
                  '회원가입',
                  style: TextStyle(color: Colors.white, fontSize: 25),
                ),
              ),
            ),

            SizedBox(
              height: 20,
            ),

            Container(
                height: 50,
                width: 250,
                decoration: BoxDecoration(
                    color: Colors.red, borderRadius: BorderRadius.circular(20)),
                child: ElevatedButton(
                  onPressed: _callAPI,
                  child: const Text('Call API'),
                )),

            SizedBox(
              height: 130,
            ),

            Text('New User? Create Account')
          ],
        ),
      ),
    );
  }
}
