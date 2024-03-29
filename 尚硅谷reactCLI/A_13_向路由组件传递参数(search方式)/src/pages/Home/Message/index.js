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
    render() {
        const {messageArr} = this.state;
        return (
            <div>
                <ul>
                    {
                        messageArr.map((msgObj)=>{
                            return (
                                <li key={msgObj.id}>
                                    {/*向路由组件传递search参数*/}
                                    <NavLink to={`/home/message/detail/?id=${msgObj.id}&title=${msgObj.title}`}>{msgObj.title}</NavLink>&nbsp;&nbsp;
                                </li>
                            )
                        })
                    }
                </ul>
                <hr/>
                {/*声明接收search参数：无需声明接收，正常注册路由即可*/}
                <Route path="/home/message/detail" component={Detail} />
            </div>

    );
    }
}

export default Index;