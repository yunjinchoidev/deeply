import React from 'react';
import RegisterForm from "../components/common/RegisterForm";

const RegisterPage = () => {
    return (
        <div style={{border:`2px solid pink`, width:`500px`, margin:"auto", padding:`20px`}}>
            <h2>회원가입</h2>
            <br/><br/>
            <RegisterForm></RegisterForm>
        </div>
    );
};

export default RegisterPage;
