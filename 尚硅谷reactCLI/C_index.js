import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {BrowserRouter} from "react-router-dom";

export const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>
);
//
// ReactDOM.render(
//     <BrowserRouter>
//         <App />
//     </BrowserRouter>,
// document.getElementById('root'));
