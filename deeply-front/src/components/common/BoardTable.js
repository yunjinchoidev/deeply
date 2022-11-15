import React, {useEffect, useState} from 'react';
import {Table} from "react-bootstrap";
import {useDispatch, useSelector} from "react-redux";
import {BOARD_LIST, LOGIN_USER} from "../../actions/types";
import {boardList, boardListAction} from "../../actions/board_actions";
import BoardListPage from "../../pages/BoardListPage";

const BoardTable = () => {

    const [totalCnt, setTotalCnt] = useState("");
    const [myBoardList, setMyBoardList] = useState([]);
    const [result, setResult] = useState([]);
    const [g, setG] = useState({});


    const dispatch = useDispatch()

    let body = {
        page: 1,
        pageSize:10,
        orderBy:'id'
    }

    // const result = useSelector(state => state.payload);
    // console.log("result"+result)
    useEffect(() => {
        console.log("rendering~");
        const g = dispatch(boardListAction(body))
            .then(response => {
                if (response.payload){
                    setResult(response.payload)
                    return
                }else{
                    console.log("Error")
                    return
                }
            })
    },[g]);


    // const result = useSelector(state => state.board);
    // const result2 = useSelector(state => state.boardListSuccess);
    // console.log("result"+result)
    // console.log("result2"+result2)
    const content = result.map((item) => ( // 리스트를 하나씩 살펴본다
        <tr key={item.id}>
            <td>{item.id}</td>
            <td>{item.author}</td>
            <td>{item.title}</td>
            <td>{item.contents}</td>
        </tr>
    ));

    return (
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>글 번호</th>
                <th>글쓴이</th>
                <th>제목</th>
                <th>내용</th>
            </tr>
            </thead>
            <tbody>
            {content}


            <tr>
                <td>3</td>
                <td colSpan={2}>Larry the Bird</td>
                <td>@twitter</td>
            </tr>
            </tbody>
        </Table>
    );
};

export default BoardTable;