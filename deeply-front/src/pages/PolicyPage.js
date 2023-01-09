import React from 'react';
import {useParams} from "react-router-dom";

const PolicyPage = () => {

    const params = useParams()

    return (
        <div>
            <div style={{border:`3px solid red`, width:`600px`, margin:`auto`, padding:`20px`}}>
                <h1> 디플리의 정책</h1><br/>
                <h3>1. 00시 정각에 하루에 3명의 사람을 추천해준다.</h3>
                <h3>2. 운영 정보를 투명하게 공개합니다.</h3>
                <h3>3. 모든 것은 무료로 제공됩니다.</h3>
            </div>
        </div>
    );
};

export default PolicyPage;