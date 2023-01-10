import React, {useEffect, useState} from 'react';
import BoardPage from "../../pages/BoardPage";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import {Link} from "react-router-dom";
import {InputGroup} from "react-bootstrap";
import {boardGetAction, boardListAction} from "../../actions/board_actions";
import {useDispatch} from "react-redux";

const BoardGet = () => {


    let [result, setResult] = useState([]);
    const dispatch = useDispatch()

    useEffect(() => {
        console.log("rendering~");
        const r = dispatch(boardGetAction({"id":41}))
            .then(response => {
                if (response.payload) {
                    setResult(response.payload)
                    return
                } else {
                    console.log("Error")
                    return
                }
            })
    }, [result]);


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
                        readOnly
                        value={result.title}
                    />
                </InputGroup>

                <InputGroup size="lg" style={{height: `400px`}}>
                    <InputGroup.Text id="inputGroup-sizing-lg">
                       내용
                    </InputGroup.Text>
                    <Form.Control
                        aria-label="Large"
                        aria-describedby="inputGroup-sizing-sm"
                        readOnly
                        value={result.content}
                    />
                </InputGroup>


                <br/>
                <br/>
                <Button variant="primary" type="submit">나도 글쓰기</Button>{' '}
                <Link to="/board">
                    <button type="primary" className="btn btn-success">목록으로</button>
                </Link>{' '}
                <button type="reset" className="btn btn-info">초기화</button>
                {' '}
            </Form>

        </div>
    );
};

export default BoardGet;