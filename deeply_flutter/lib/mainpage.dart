import 'package:deeply_flutter/main.dart';
import 'package:deeply_flutter/mainpages/loginscreen.dart';
import 'package:deeply_flutter/mainpages/mypagescreen.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'mainpages/homescreen.dart';
import 'mainpages/matchingsreen.dart';
import 'mainpages/registerscreen.dart';
import 'mainpages/tabbar.dart';
import 'package:persistent_bottom_nav_bar/persistent_tab_view.dart';



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
    ),
    Container(
      child: TabPage(),
    ),
    Container(
      child: HomeScreen(),
    ),
    Container(
      child: InfoPage(),
    ),
    Container(
      child: Center(child: Text('상점.'),),
    ),
    Container(
      child: MyPageApp(),
    ),
  ];


  @override
  Widget build(BuildContext context) {

    PersistentTabController _controller;

    _controller = PersistentTabController(initialIndex: 0);

    List<Widget> _buildScreens() {
      return [
        TabPage(),
        LoginApp(),
        MyPageApp()
      ];
    }



    List<PersistentBottomNavBarItem> _navBarsItems() {
      return [
        PersistentBottomNavBarItem(
          icon: Icon(CupertinoIcons.home),
          title: ("Home"),
          activeColorPrimary: CupertinoColors.activeBlue,
          inactiveColorPrimary: CupertinoColors.systemGrey,
        ),
        PersistentBottomNavBarItem(
          icon: Icon(CupertinoIcons.settings),
          title: ("Settings"),
          activeColorPrimary: CupertinoColors.activeBlue,
          inactiveColorPrimary: CupertinoColors.systemGrey,
        ),
        PersistentBottomNavBarItem(
          icon: Icon(CupertinoIcons.settings),
          title: ("로그인"),
          activeColorPrimary: CupertinoColors.activeBlue,
          inactiveColorPrimary: CupertinoColors.systemGrey,
        ),


      ];
    }



    return Scaffold(

      appBar: AppBar(title: Text('Deeply',style: TextStyle(fontFamily: 'bamin'),), ),

      bottomNavigationBar: PersistentTabView(
        context,
        controller: _controller,
        screens: _buildScreens(),
        items: _navBarsItems(),
        confineInSafeArea: true,
        backgroundColor: Colors.white, // Default is Colors.white.
        handleAndroidBackButtonPress: true, // Default is true.
        resizeToAvoidBottomInset: true, // This needs to be true if you want to move up the screen when keyboard appears. Default is true.
        stateManagement: true, // Default is true.
        hideNavigationBarWhenKeyboardShows: true, // Recommended to set 'resizeToAvoidBottomInset' as true while using this argument. Default is true.
        decoration: NavBarDecoration(
          borderRadius: BorderRadius.circular(10.0),
          colorBehindNavBar: Colors.white,
        ),
        popAllScreensOnTapOfSelectedTab: true,
        popActionScreens: PopActionScreensType.all,
        itemAnimationProperties: ItemAnimationProperties( // Navigation Bar's items animation properties.
          duration: Duration(milliseconds: 200),
          curve: Curves.ease,
        ),
        screenTransitionAnimation: ScreenTransitionAnimation( // Screen transition animation on change of selected tab.
          animateTabTransition: true,
          curve: Curves.ease,
          duration: Duration(milliseconds: 200),
        ),
        navBarStyle: NavBarStyle.style1, // Choose the nav bar style with this property.
      )


      // BottomNavigationBar(
      //   type: BottomNavigationBarType.fixed,
      //   backgroundColor: Colors.white,
      //   selectedItemColor: Colors.black,
      //   unselectedItemColor: Colors.grey.withOpacity(.60),
      //   selectedFontSize: 15,
      //   unselectedFontSize: 10,
      //   currentIndex: _seletedIndex,
      //   onTap: (int index){
      //     setState((){
      //       _seletedIndex = index;
      //     });
      //   },
      //   items: bottomItem,
      // ),


      // body: pages[_seletedIndex],
    );
  }
}
