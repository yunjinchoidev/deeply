import React from 'react';
import {Link, Outlet} from "react-router-dom";

const Layout = () => {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <h2 className="navbar-brand" href="#" style={{fontSize:`30px`}}>디플리</h2>
                <div className="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul className="navbar-nav">
                        <li className="nav-item active"><a className="nav-link" href="/">홈</a></li>
                        <li className="nav-item"><a className="nav-link" href="/login">로그인</a></li>
                        <li className="nav-item"><a className="nav-link" href="/logout">로그아웃</a></li>
                        <li className="nav-item"><a className="nav-link" href="/register">회원가입</a></li>
                        <li className="nav-item"><a className="nav-link" href="/mypage">마이페이지</a></li>
                        <li className="nav-item"><a className="nav-link" href="/customercenter">고객센터</a></li>
                        <li className="nav-item"><a className="nav-link" href="/board">게시판</a></li>
                        <li className="nav-item"><a className="nav-link" href="/matchList">매칭상대</a></li>
                        <li className="nav-item"><a className="nav-link" href="/policy">정책</a></li>
                        <li className="nav-item"><a className="nav-link" href="/admin">관리자페이지</a></li>
                    </ul>
                </div>
            </nav>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <a className="navbar-brand" href="#">사랑은 현실이 됩니다. 아름다운 황혼은 당신 것입니다.</a>
            </nav>
            <hr/>
            <main>
                <Outlet/>
            </main>
        </div>
    );
};

export default Layout;
