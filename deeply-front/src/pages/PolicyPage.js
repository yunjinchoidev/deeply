import React from 'react';
import {useParams} from "react-router-dom";

const PolicyPage = () => {

    const params = useParams()
    //
    const customerPolicy = params.detail

    return (
        <div>
            <h2>
                { customerPolicy ?
                    (<p>customer</p>)
                    : (<p>일반</p>) }
                정책 페이지 입니다.</h2>

        </div>
    );
};

export default PolicyPage;