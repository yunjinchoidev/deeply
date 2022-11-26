import 'package:deeply_flutter/main.dart';
import 'package:deeply_flutter/mainpages/loginscreen.dart';
import 'package:deeply_flutter/mainpages/mypagescreen.dart';
import 'package:flutter/material.dart';

import 'mainpages/homescreen.dart';
import 'mainpages/matchingsreen.dart';
import 'mainpages/registerscreen.dart';

class MainPage extends StatefulWidget {
  const MainPage({Key? key}) : super(key: key);

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {

  get items => null;

  int _seletedIndex = 0;
  List<BottomNavigationBarItem> bottomItem=[
    BottomNavigationBarItem(icon: Icon(Icons.login), label: '로그인'),
    BottomNavigationBarItem(icon: Icon(Icons.app_registration), label: '회원가입'),
    BottomNavigationBarItem(icon: Icon(Icons.home), label: '홈'),
    BottomNavigationBarItem(icon: Icon(Icons.meeting_room), label: '매칭 서비스'),
    BottomNavigationBarItem(icon: Icon(Icons.shop), label: '상점'),
    BottomNavigationBarItem(icon: Icon(Icons.security), label: '마이페이지'),
  ];

  List pages = [
    Container(
      child: LoginApp(),
      // Center(child: Text('디플리 홈'),),
    ),
    Container(
      child: RegisterApp(),
      // Center(child: Text('디플리 홈'),),
    ),
    Container(
      child: HomeScreen(),
      // Center(child: Text('디플리 홈'),),
    ),
    Container(
      child: InfoPage(),
      // child: Center(child: Text('매칭 서비스'),),
    ),
    Container(
      child: Center(child: Text('상점.'),),
    ),
    Container(
      // child: Center(child: Text('마이페이지.'),),
      child: MyPageApp(),
    ),
  ];


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Deeply',style: TextStyle(fontFamily: 'bamin'),), ),
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        backgroundColor: Colors.white,
        selectedItemColor: Colors.blue,
        unselectedItemColor: Colors.grey.withOpacity(.60),
        onTap: (int index){
          setState((){
            _seletedIndex = index;
          });
        },
        items: bottomItem,
      ),
      body: pages[_seletedIndex],
    );
  }
}
