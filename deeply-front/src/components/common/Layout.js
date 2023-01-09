import React from 'react';
import {Link, Outlet} from "react-router-dom";
import {logoutUser} from "../../actions/user_actions";
import {useDispatch} from "react-redux";

const Layout = () => {

    const authrity = localStorage.getItem("authority")
    console.log("authority"+authrity)

    const dispatch = useDispatch()
    const logoutDB = (e) => {
        console.log("로그아웃 시도")
        dispatch(logoutUser())
    }

    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light" style={{borderBottom:`3px solid grey`}}>
                <a className="navbar-brand" href="/" style={{fontSize:`40px`, paddingLeft:`20px`, fontWeight:`bolder`}}>디플리</a>
                <div className="collapse navbar-collapse" id="navbarNavDropdown" style={{fontSize:`20px`, fontWeight:`bolder`}}>
                    <ul className="navbar-nav">

                        {/*전체공개*/}
                        <li className="nav-item active"><a className="nav-link" href="/">홈 |</a></li>

                        {/*비로그인유저*/}
                        {authrity == null && <li className="nav-item"><a className="nav-link" href="/login" style={{color:'red'}}>로그인 |</a></li>}
                        {authrity == null && <li className="nav-item"><a className="nav-link" href="/register" style={{color:'red'}}>회원가입 |</a></li>}

                        {/*로그인유저*/}
                        {authrity != null && <li className="nav-item"><a className="nav-link" href="/logout" style={{color:'blue'}}
                                                        onClick={() => logoutDB()}>로그아웃하기 |</a></li>}
                        {authrity != null && <li className="nav-item"><a className="nav-link" href="/mypage" style={{color:'blue'}}>마이페이지 |</a></li>}

                        {/*전체공개*/}
                        <li className="nav-item"><a className="nav-link" href="/board">자유게시판 |</a></li>
                        <li className="nav-item"><a className="nav-link" href="/policy">정책 |</a></li>

                        {/*관리자유저*/}
                        {authrity == 2 && <li className="nav-item"><a className="nav-link" href="/admin">관리자페이지 |</a></li>}

                        {/*<li className="nav-item"><a className="nav-link" href="/sociallogin">소셜로그인</a></li>*/}
                        {/*<li className="nav-item"><a className="nav-link" href="/MyFeedPage.js">나의피드</a></li>*/}
                        {/*<li className="nav-item"><a className="nav-link" href="/customercenter">고객센터</a></li>*/}
                        {/*<li className="nav-item"><a className="nav-link" href="/matchList">매칭상대</a></li>*/}
                        {/*<li className="nav-item"><a className="nav-link" href="/notice">공지사항</a></li>*/}
                        {/*<li className="nav-item"><a className="nav-link" href="/basket">장바구니</a></li>*/}
                        {/*<li className="nav-item"><a className="nav-link" href="/order">주문하기</a></li>*/}
                        {/*<li className="nav-item"><a className="nav-link" href="/mapservice">지도 매칭 서비스</a></li>*/}
                        {/*<li className="nav-item"><a className="nav-link" href="/chat">채팅 서비스</a></li>*/}
                    </ul>
                </div>
            </nav>

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
