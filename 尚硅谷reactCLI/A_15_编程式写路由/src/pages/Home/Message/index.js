import React, {Component} from 'react';
import {NavLink,Route} from 'react-router-dom'
import Detail from './Detail';

class Index extends Component {
    state = {
        messageArr:[
            {id:'01',title:'消息1'},
            {id:'02',title:'消息2'},
            {id:'03',title:'消息3'}
        ]
    }
    replaceShow = (id,title)=>{
        //编写一段代码，让其实现跳转到Detail组件，且为replace跳转,【携带params参数】
        this.props.history.replace(`/home/message/detail/${id}/${title}`)
        //【携带search参数】
        // this.props.history.replace(`/home/message/detail/?id=${id}&title=${title}`)
    }
    pushShow = (id,title)=>{
        //编写一段代码，让其实现跳转到Detail组件，且为push跳转，【携带params参数】
        this.props.history.push(`/home/message/detail/${id}/${title}`)
        //【携带search参数】
        // this.props.history.push(`/home/message/detail/?id=${id}&title=${title}`)
    }
    render() {
        const {messageArr} = this.state;
        return (
            <div>
                <ul>
                    {
                        messageArr.map((msgObj)=>{
                            return (
                                <li key={msgObj.id}>
                                    {/*向路由组件传递携带params参数*/}
                                    <NavLink to={`/home/message/detail/${msgObj.id}/${msgObj.title}`}>{msgObj.title}</NavLink>&nbsp;&nbsp;
                                    &nbsp;<button onClick={()=>this.pushShow(msgObj.id,msgObj.title)}>push查看</button>
                                    {/*高阶函数，传递参数，此处this.replaceShow(..) 标识用户自己调用的，非React调用*/}
                                    &nbsp;<button onClick={()=>this.replaceShow(msgObj.id,msgObj.title)}>replace查看</button>
                                </li>
                            )
                        })
                    }
                </ul>
                <hr/>
                {/*生命接收params参数*/}
                <Route path="/home/message/detail/:id/:title" component={Detail} />
            </div>
    );
    }
}

export default Index;