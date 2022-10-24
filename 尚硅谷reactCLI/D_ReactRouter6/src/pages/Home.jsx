import React,{useState} from 'react';
import {Navigate,Outlet, NavLink} from "react-router-dom";

function Home(props) {
    //使用useState调用状态
    const [sum,setSum] = useState(1)
    return (
        <div>
            <h3>我是Home内容</h3>
            {sum === 2 ? <Navigate to={"/about"} replace={false}/> : <h4>当前的sum值时{sum}</h4>}
            <button onClick={()=>{setSum(2)}}>点击切换sum值</button>

            <ul>
                <li>
                    <NavLink to='/home/news'>News</NavLink>
                </li>
                <li>
                    <NavLink to='/home/message'>Message</NavLink>
                </li>
            </ul>
            {/*指定路由呈现的位置*/}
            <Outlet/>
        </div>
    );
}

export default Home;