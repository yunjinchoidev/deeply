import React from 'react';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import BoardWriteForm from "../components/common/BoardWriteForm";

const BoardPage = () => {
    return (
        <div>
            <h2>글 작성 폼</h2>
            <BoardWriteForm></BoardWriteForm>
        </div>
    );
};

export default BoardPage;