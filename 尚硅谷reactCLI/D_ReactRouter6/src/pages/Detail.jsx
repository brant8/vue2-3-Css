import React from 'react';
import {useParams,useMatch,useSearchParams,useLocation} from "react-router-dom";

export default function Detail(props) {
    //useParams获取接收到的对象参数
    // const {id,title,content} = useParams()
    //useMatch解析地址获得参数对象
    // const x = useMatch('/home/message/detail/:id/:title/:content');

    //search更新的参数，setSearch更新的方法; map键值对
    // const [search,setSearch] = useSearchParams();
    // const id = search.get('id')
    // const title = search.get('title')
    // const content = search.get('content')
    // const y = useLocation();
    // console.log('useLocation:',y)

    // const {state} = useLocation()
    const {state:{id,title,content}} = useLocation() //连续解构赋值
    return (
        <div>
            {/*<button onClick={()=>{setSearch('id=008&title=嘿嘿&content=喜喜')}}>点击更新以下收到的search参数</button>*/}
            <li>{id}</li>
            <li>{title}</li>
            <li>{content}</li>
        </div>
    );
}

