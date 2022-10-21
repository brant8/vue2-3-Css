import React, {Component} from 'react';

const data = [
    {id:'01',content:'你好中国1'},
    {id:'02',content:'你好中国2'},
    {id:'03',content:'你好中国3'},
];

class Detail extends Component {
    render() {
        console.log(this.props)
        /* match:
        *    isExact:true
        *    params :{id: '02', title: '消息2'} */
        //接收params参数
        const {id,title} = this.props.match.params;
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