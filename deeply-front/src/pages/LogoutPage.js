import React, {useEffect, useState} from 'react';
import Button from "react-bootstrap/Button";
import {loginUser, logoutUser} from "../actions/user_actions";
import {useDispatch} from "react-redux";

const LogoutPage = () => {

    const dispatch = useDispatch()
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const logoutDB = (e) => {
        console.log("로그아웃 시도")
        dispatch(logoutUser())
    }
    logoutDB()

    useEffect(() => {
        console.log("LoginPage render ... ");
    })


    return (
        <div>
            {/*<h2>로그아웃 페이지</h2>*/}
            {/*<Button variant="warning" type="button"*/}
            {/*        onClick={logoutDB()}>로그아웃</Button>{' '}*/}
        </div>
    );
};

export default LogoutPage;