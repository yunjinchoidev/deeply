import React from 'react';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import BoardWriteForm from "../components/common/BoardWriteForm";

const BoardPage = () => {
    return (
        <div style={{width:`800px`, height:`1000px`, margin:`auto`, border:`4px solid pink`, padding:`30px` }}>
            <h1>자유게시판</h1>
            <BoardWriteForm></BoardWriteForm>
        </div>
    );
};

export default BoardPage;