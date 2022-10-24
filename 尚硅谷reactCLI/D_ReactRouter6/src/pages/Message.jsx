import React,{useState} from 'react';
import {Link,Outlet,useNavigate} from "react-router-dom";


function Message(props) {
    const [messages] = useState([
        {id:'001',title:'消息1',content:'锄禾日当午1'},
        {id:'002',title:'消息2',content:'锄禾日当午2'},
        {id:'003',title:'消息3',content:'锄禾日当午3'},
        {id:'004',title:'消息4',content:'锄禾日当午4'},
    ])

    const navigate = useNavigate();
    function showDetail (m){
        /**navigate(路径[，配置对象])*/
        //子路由不带斜杠
        navigate('about',{
            replace:false,
            state:{
                id:m.id,
                title:m.title,
                content:m.content
            }
        })
    }
    return (
        <div>
            <h3>message page</h3>
            <ul>
                {
                    messages.map((m)=>{
                        return (
                            <li key={m.id}>
                                {/*<Link to={`detail/${m.id}/${m.title}/${m.content}`}>{m.title}</Link>*/}
                                {/*<Link to={`detail?id=${m.id}&title=${m.title}&content=${m.content}`}>{m.title}</Link>*/}
                                <Link to='detail' state={
                                    {
                                        id:m.id,
                                        title:m.title,
                                        content:m.content
                                    }
                                }>{m.title}</Link>
                                <button onClick={()=>showDetail(m)}>查看详情</button>
                            </li>
                        )
                    })
                }
            </ul>
            <hr/>
            <Outlet/>
        </div>
    );
}

export default Message;