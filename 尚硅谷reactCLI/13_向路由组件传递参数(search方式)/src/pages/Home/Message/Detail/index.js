import React, {Component} from 'react';
import qs from 'qs'
/*
let obj = {name:'tom',age:18} //-> name=tom&age=18； key=value&key=value为urlencoded编码
console.log(qs.stringify(obj))
let str = 'carName=奔驰&price=199';
console.log(qs.parse(str))
*/

const data = [
    {id:'01',content:'你好中国1'},
    {id:'02',content:'你好中国2'},
    {id:'03',content:'你好中国3'},
];

class Detail extends Component {
    render() {
        console.log(this.props)
        /* location:
         *   hash: ""
         *   key: "sugwfl"
         *   pathname: "/home/message/detail/"
         *   search: "?id=01&消息1" */
        //接收search参数
        const {search} = this.props.location;
        console.log(search) //?id=03&消息3
        const result = qs.parse(search.slice(1));
        console.log(result);//{id: '01', title: '消息1'}
        const {id,title} = result;
        const findResult = data.find((detailObj)=>{
            return detailObj.id === id;
        })
        return (
            <ul>
                <li>ID2:{id}</li>
                <li>Title:{title}</li>
                <li>Content:{findResult.content}</li>
            </ul>
        );
    }
}

export default Detail;