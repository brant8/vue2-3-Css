import React, {Component} from 'react';
import './index.css';

class Index extends Component {
    handleCheckAll=(event)=>{
        this.props.checkAllTodo(event.target.checked);
    }
    //清除所有已完成的
    handleClearAllDone=()=>{
        this.props.clearAllDone();
    };
    render() {
        const {todos} = this.props;
        //已完成的个数
        const doneCount = todos.reduce((pre,current)=>{
            return pre + (current.done ? 1: 0);
        },0)
        console.log(doneCount)
        return (
            <div className="todo-footer">
                <label>
                    <input type="checkbox" onChange={this.handleCheckAll} checked={doneCount===todos.length && todos.length !==0 ? true : false}/>
                </label>
                <span>
                          <span>已完成{doneCount}</span> / 全部{todos.length}
                        </span>
                <button onClick={this.handleClearAllDone} className="btn btn-danger">清除已完成任务</button>
            </div>
        );
    }
}

export default Index;