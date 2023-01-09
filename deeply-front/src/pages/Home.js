import React from 'react';
import {Link} from "react-router-dom";

const Home = () => {
    return (
        <div>
            <h2 style={{textAlign: `center`, color: `deeppink`}}>디플리에 방문하신 여러분을 환영합니다.</h2>
            <a className="navbar-brand" href="#"
               style={{fontSize: `30px`, textAlign: `center`, fontWeight:`bolder`}}
            >사랑은 현실이 됩니다. 아름다운 황혼은 당신 것입니다.</a>
            <br/>
            <br/>
            <br/>
            <img src="/deeply.png"
                 style={{width: `300px`, border: `2px solid black`, margin: "auto", display: "block"}}/>
            <br/>
            <br/>
            <br/>
            <h2>디플리 연혁</h2>
            <h3>2022.11.15 ~ 개발 시작</h3>
        </div>
    );
};

export default Home;
