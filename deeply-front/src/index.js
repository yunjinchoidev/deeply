import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.css';
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";

// redux things

// 다운로드 받은 두 패키지

import {applyMiddleware, compose, createStore} from "redux";
import {composeWithDevTools} from 'redux-devtools-extension';
import PromiseMiddleware from "redux-promise"
import { createLogger } from 'redux-logger';
import ReduxThunk from "redux-thunk"
import Reducers from "./reducers";
import promiseMiddleware from "redux-promise";
import createSagaMiddleware from 'redux-saga';


const logger = createLogger();
const root = ReactDOM.createRoot(document.getElementById('root'));
const sagaMiddleware = createSagaMiddleware();
const createStoreWithMiddleware = applyMiddleware(promiseMiddleware, ReduxThunk)(createStore)

// const store = createStore(
//     rootReducer,
//     composeWithDevTools(applyMiddleware(logger, ReduxThunk, sagaMiddleware))
// );

root.render(
    <BrowserRouter>
        <React.StrictMode>
            <Provider store={createStoreWithMiddleware(Reducers,
                composeWithDevTools()
                )}>
                <App/>
            </Provider>
        </React.StrictMode>
    </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
