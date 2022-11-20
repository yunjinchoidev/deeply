import React from 'react';
import {useParams} from "react-router-dom";

const PolicyPage = () => {

    const params = useParams()

    return (
        <div>
            <h2> 정책 페이지 입니다.</h2>
            <h1>1. 00시 정각에 하루에 5명의 사람을 추천해준다.</h1>
            <h1>2. 정보를 보관하지 않습니다.</h1>

        </div>
    );
};

export default PolicyPage;