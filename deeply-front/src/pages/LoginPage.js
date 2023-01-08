import React from 'react';
import LoginForm from "../components/common/LoginForm";

const LoginPage = () => {
    return (
        <div style={{border:`2px solid pink`, width:`400px`, margin:"auto", padding:`20px`}}>
            <h2>로그인</h2>
            <LoginForm></LoginForm>
        </div>
    );
};

export default LoginPage;
