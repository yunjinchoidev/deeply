import React from 'react';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import BoardWriteForm from "../components/common/BoardWriteForm";
import BoardGet from "../components/common/BoardGet";

const BoardGetPage = () => {
    return (
        <div style={{width:`800px`, height:`1000px`, margin:`auto`, border:`4px solid pink`, padding:`30px` }}>
            <h1>글 조회</h1>
            <BoardGet></BoardGet>
        </div>
    );
};

export default BoardGetPage;