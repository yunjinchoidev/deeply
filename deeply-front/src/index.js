import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.css';
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";

import {applyMiddleware, createStore} from "redux";
import {composeWithDevTools} from 'redux-devtools-extension';
import promiseMiddleware from "redux-promise"
import ReduxThunk from "redux-thunk"
import Reducers from "./reducers";

const root = ReactDOM.createRoot(document.getElementById('root'));
const createStoreWithMiddleware = applyMiddleware(promiseMiddleware, ReduxThunk)(createStore)

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
