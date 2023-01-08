import React from 'react';
import {Link, Outlet} from "react-router-dom";

const Layout = () => {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light" style={{borderBottom:`3px solid grey`}}>
                <a className="navbar-brand" href="#" style={{fontSize:`40px`, paddingLeft:`20px`, fontWeight:`bolder`}}>디플리</a>
                <div className="collapse navbar-collapse" id="navbarNavDropdown" style={{fontSize:`20px`, fontWeight:`bolder`}}>
                    <ul className="navbar-nav">
                        <li className="nav-item active"><a className="nav-link" href="/">홈</a></li>
                        <li className="nav-item"><a className="nav-link" href="/login">로그인</a></li>
                        {/*<li className="nav-item"><a className="nav-link" href="/sociallogin">소셜로그인</a></li>*/}
                        <li className="nav-item"><a className="nav-link" href="/logout">로그아웃</a></li>
                        <li className="nav-item"><a className="nav-link" href="/register">회원가입</a></li>
                        <li className="nav-item"><a className="nav-link" href="/mypage">마이페이지</a></li>
                        <li className="nav-item"><a className="nav-link" href="/MyFeedPage.js">나의피드</a></li>
                        <li className="nav-item"><a className="nav-link" href="/customercenter">고객센터</a></li>
                        <li className="nav-item"><a className="nav-link" href="/board">게시판</a></li>
                        <li className="nav-item"><a className="nav-link" href="/matchList">매칭상대</a></li>
                        <li className="nav-item"><a className="nav-link" href="/policy">정책</a></li>
                        <li className="nav-item"><a className="nav-link" href="/admin">관리자페이지</a></li>
                        <li className="nav-item"><a className="nav-link" href="/notice">공지사항</a></li>
                        <li className="nav-item"><a className="nav-link" href="/basket">장바구니</a></li>
                        <li className="nav-item"><a className="nav-link" href="/order">주문하기</a></li>
                        <li className="nav-item"><a className="nav-link" href="/mapservice">지도 매칭 서비스</a></li>
                        <li className="nav-item"><a className="nav-link" href="/chat">채팅 서비스</a></li>
                    </ul>
                </div>
            </nav>
            {/*<nav className="navbar navbar-expand-lg navbar-light bg-light" style={{border:`2px black`}}>*/}
            {/*    <a className="navbar-brand" href="#"*/}
            {/*        style={{fontSize:`30px`, textAlign:`center`, color:`red`}}*/}
            {/*        >사랑은 현실이 됩니다. 아름다운 황혼은 당신 것입니다.</a>*/}
            {/*</nav>*/}
            <br/>
            <br/>
            <br/>
            <br/>
            <main>
                <Outlet/>
            </main>
        </div>
    );
};

export default Layout;
