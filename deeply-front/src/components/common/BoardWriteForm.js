import React from 'react';
import BoardPage from "../../pages/BoardPage";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import {Link} from "react-router-dom";
import {InputGroup} from "react-bootstrap";

const BoardWriteForm = () => {
    return (
        <div>
            <Form>
                <InputGroup className="mb-3">
                    <InputGroup.Text id="inputGroup-sizing-default">
                        제목
                    </InputGroup.Text>
                    <Form.Control
                        aria-label="Default"
                        aria-describedby="inputGroup-sizing-default"
                    />
                </InputGroup>

                <InputGroup size="lg" style={{height: `400px`}}>
                    <InputGroup.Text id="inputGroup-sizing-lg">내용</InputGroup.Text>
                    <Form.Control
                        aria-label="Large"
                        aria-describedby="inputGroup-sizing-sm"
                    />
                </InputGroup>


                <br/>
                <br/>
                <Button variant="primary" type="submit">글쓰기</Button>{' '}
                <Link to="/board"> <button type="primary" className="btn btn-success">목록으로</button></Link>{' '}
                <button type="reset" className="btn btn-info">초기화</button>{' '}
            </Form>

        </div>
    );
};

export default BoardWriteForm;