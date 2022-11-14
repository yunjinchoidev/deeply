import React from 'react';
import {Link, Outlet} from "react-router-dom";

const Layout = () => {
    return (
        <div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <a className="navbar-brand" href="#">디플리</a>

                    <div className="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul className="navbar-nav">
                            <li className="nav-item active">
                                <a className="nav-link" href="/">홈</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/login">로그인</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/logout">로그아웃</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/register">회원가입</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/mypage">마이페이지</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/customercenter">고객센터</a>
                            </li>

                        </ul>
                    </div>
                </nav>
            <main>
                <Outlet/>
            </main>
        </div>
    );
};

export default Layout;