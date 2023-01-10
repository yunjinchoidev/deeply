import React, {useEffect, useState} from 'react';
import {Nav, Table} from "react-bootstrap";
import {useDispatch, useSelector} from "react-redux";
import {BOARD_LIST, LOGIN_USER} from "../../actions/types";
import {boardGetAction, boardList, boardListAction} from "../../actions/board_actions";
import BoardListPage from "../../pages/BoardListPage";
import {Link} from "react-router-dom";
import Button from "react-bootstrap/Button";
import {loginUser} from "../../actions/user_actions";
import {displayFile} from "../../actions/file_actions";

const BoardTable = () => {

    const [totalCnt, setTotalCnt] = useState("");
    const [myBoardList, setMyBoardList] = useState([]);
    const [result, setResult] = useState([]);
    const [g, setG] = useState({});
    const [page, setPage] = useState({});


    const dispatch = useDispatch()

    let body = {
        page: 1,
        pageSize: 10,
        orderBy: 'id'
    }

    // const result = useSelector(state => state.payload);
    // console.log("result"+result)
    useEffect(() => {
        console.log("rendering~");
        const r = dispatch(boardListAction(body))
            .then(response => {
                if (response.payload) {
                    setResult(response.payload)
                    console.log(result)
                    return
                } else {
                    console.log("Error")
                    return
                }
            })
    }, [g]);


    const onClickBoard = (id) => {
        let body = {
            id: id,
        }
        const res = dispatch(boardGetAction(body))
            .then(response => {
                if (response.payload){
                    console.log("response:"+response.payload.id)
                    console.log("response:"+response.payload.content)
                    console.log("response:"+response.payload.id)
                    console.log("response:"+response.payload.id)
                    window.location.href = "/board/id/"+`${response.payload.id}`
                    return
                }else{
                    console.log("Error")
                    return
                }
            })
        console.log("?"+res.data)
        // window.location.href='/board/'+res.contents
    }


    // const result = useSelector(state => state.board);
    // const result2 = useSelector(state => state.boardListSuccess);
    // console.log("result"+result)
    // console.log("result2"+result2)
    const content = result.map((item) => ( // 리스트를 하나씩 살펴본다
        <tr key={item.id} onClick={() => onClickBoard(item.id)}>
            <td>{item.id}</td>
            <td>{item.author}</td>
            <td>{item.title}</td>
            <td>{item.contents}</td>
        </tr>
    ));

    return (
        <div style={{width: `1300px`, margin: `auto`, border: `3px solid pink`, padding: `30px`}}>
            <Table striped bordered hover style={{cursor: `pointer`}}>
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
                </tbody>

            </Table>

            <Nav>
                <Button onClick={() => setPage(page - 1)} disabled={page === 1}>
                    &lt;
                </Button>
                {/*{Array(numPages)*/}
                {/*    .fill()*/}
                {/*    .map((_, i) => (*/}
                {/*        <Button*/}
                {/*            key={i + 1}*/}
                {/*            onClick={() => setPage(i + 1)}*/}
                {/*            aria-current={page === i + 1 ? "page" : null}*/}
                {/*        >*/}
                {/*            {i + 1}*/}
                {/*        </Button>*/}
                {/*    ))}*/}
                {/*<Button onClick={() => setPage(page + 1)} disabled={page === numPages}>*/}
                {/*    &gt;*/}
                {/*</Button>*/}
            </Nav>

            <nav aria-label="Page navigation example"
                 style={{margin: `0`, padding: `0`, textAlign: `center`, listStyleType: `none`, width: `1000px`}}>
                <ul className="pagination" style={{paddingLeft: `400px`}}>
                    <li className="page-item"><a className="page-link" href="#">맨 처음</a></li>
                    <li className="page-item"><a className="page-link" href="#">이전</a></li>
                    <li className="page-item"><a className="page-link" href="#">1</a></li>
                    <li className="page-item"><a className="page-link" href="#">다음</a></li>
                    <li className="page-item"><a className="page-link" href="#">맨 마지막</a></li>
                </ul>
            </nav>
            <Link to="/board/write">
                <button type="button" className="btn btn-success">글쓰기</button>
            </Link>

        </div>
    );
};

export default BoardTable;