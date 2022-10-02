import React, {Component} from 'react';
import {nanoid} from 'nanoid';
import PropTypes from 'prop-types';
import './index.css';

class Index extends Component {
    handleKeyUp = (event)=>{
        //结构赋值获取keyCode,target，下面的值可使用keyCode作为参数
        const {keyCode,target} = event;
        //event.keyCode获得回车确认
        if(keyCode !== 13) return
        //如果为空不能添加
        if(target.value.trim() ===''){
            alert('输入不能为空')
            return
        }
            console.log(event.target.value,event.keyCode);
            //通过App.jsx传递过来的回调函数，在子函数传递参数即可返回数据给父组件
            this.props.a(event.target.value);
        //准备好一个todo对象,使用uuid进行唯一值的设定，'npm i uuid'（库占容量大，使用nanoid小巧同时也可以生成uuid）
        const todoObj = {id:nanoid(),name:target.value,done:false};
        //将todoObj传递给App
        this.props.addTodo(todoObj);
        //回车添加完毕后，清空输入
        target.value='';
    }

    //对接收的props进行：类型、必要性的限制
    static propTypes = {
        addTodo:PropTypes.func.isRequired
    }

    render() {
        return (
                <div className="todo-header">
                    <input onKeyUp={this.handleKeyUp} type="text" placeholder="请输入你的任务名称，按回车键确认"/>
                </div>
        );
    }
}

export default Index;