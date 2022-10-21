import React, {Component} from 'react';
import './index.css';

class Index extends Component {
    state={
        mouse:false
    }
    handleMouse=(flag)=>{
        return ()=>{
            this.setState({mouse:flag});
            /**this.setState(flag);
             * Uncaught Error: setState(...): takes an object of state variables to update or a function
             * which returns an object of state variables.*/
        }
    }
    handleCheck=(id)=>{
        return (event)=>{
            // console.log(id,event.target.checked)
            this.props.updateTodo(id,event.target.checked)
        }
    }
    handleDelete=(id)=>{
        // console.log('通知App删除',id)
        if(window.confirm('确定删除吗？')) {
            this.props.deleteTodo(id);
        }
    }
    render() {
        //接收从List传过来的参数
        const {id,name,done} = this.props;
        return (
            <li style={{backgroundColor:this.state.mouse ? '#ddd' : 'white'}} onMouseLeave={this.handleMouse(false)} onMouseEnter={this.handleMouse(true)}>
                {/**/}
                <label>
                    <input type="checkbox" checked={done} onChange={this.handleCheck(id)}/>
                    {/* 使用：checked={done} 报以下错误
                    Warning: You provided a `checked` prop to a form field without an `onChange` handler.
                    This will render a read-only field. If the field should be mutable use `defaultChecked`.
                    defaultChecked={done} 后期会由bug*/}
                    <span>{name}</span>
                </label>
                <button onClick={()=>{this.handleDelete(id)}} className="btn btn-danger" style={{display:this.state.mouse?'block':'none'}}>删除</button>
            </li>
        );
    }
}

export default Index;