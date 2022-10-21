import React, {Component} from 'react';
import Item from '../Item';
import './index.css';
import PropTypes from "prop-types";

class Index extends Component {
    //对接收的props进行：类型、必要性的限制
    static propTypes = {
        updateTodo:PropTypes.func.isRequired,
        todos:PropTypes.array.isRequired
    }
    render() {
        const {todos,updateTodo,deleteTodo} = this.props;
        return (
            <ul className="todo-main">
                {
                    todos.map((todo,index)=>{
                        return <Item key={todo.id} {...todo} updateTodo={updateTodo} deleteTodo={deleteTodo} /> /*批量传递参数 ； 在Item中接收方式与傻瓜式传一样*/
                        /* 傻瓜式传递参数到ITEM中
                        return <Item key={todo.id} id={todo.id} name={todo.name} done={this.done}/> */
                        /*Warning:Each child in a list should have a unique "key" prop
                        * 能用id最好用id，否则用key*/
                    })
                }
            </ul>
        );
    }
}

export default Index;