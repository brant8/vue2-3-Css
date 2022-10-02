//创建外壳组件App
import React from 'react';
import Header from './components/Header'
import List from './components/List'
import Footer from './components/Footer'
import  './App.css';

class App extends React.Component{
    //初始化状态
    state = {
        todos:[
            {id:'001',name:'吃饭',done:true},
            {id:'002',name:'睡觉',done:true},
            {id:'003',name:'打代码',done:false},
        ]
    };
    //子传父方式：通过箭头函数，在App定义，调用子组件传递给子组件传递该箭头函数，
    a = (data)=>{
        console.log('APP...',data);
    }
    addTodo = (todoObj)=>{ //最好传递一个对象到子组件进行返回给数组对象
        console.log('App',todoObj);
        //获取原todos
        const {todos} = this.state;
        const newTodos = [todoObj,...todos];
        this.setState({todos: newTodos});
    }
    //更改勾选状态传递给子组件并返回来
    updateTodo = (id,done)=>{
        //获取状态中的todos
        const {todos} = this.state;
        const newTodos = todos.map((todoObj)=>{
            if(todoObj.id === id){
                return {...todoObj,done:done};
            }else{
                return todoObj;
            }
        })
        this.setState({todos: newTodos})
    }

    //用于删除一个todo
    deleteTodo = (id)=>{
        //获取原来的todos
        const {todos} = this.state;
        const newTodos = todos.filter((todoObj)=>{
            return todoObj.id !== id;
        })
        //更新状态
        this.setState({todos: newTodos})
    }

    //用于Footer的全选
    checkAllTodo = (done)=>{
        //获取原来的todos
        const {todos} = this.state
        //加工数据
        const newTodos = todos.map((todoObj)=>{
            return {...todoObj,done:done};
        });
        this.setState({todos:newTodos})
    }
    //清除所有已完成的
    clearAllDone=()=>{
        const {todos} = this.state;
        const newTodos = todos.filter((todoObj)=>{
            return !todoObj.done; //todoObj === false
        })
        this.setState({todos:newTodos})
    }
    render(){
        const {todos} = this.state;
        return (
            <div className="todo-container">
                <div className="todo-wrap">
                    {/* <Header a={1}/>  通过App.js给Header组件传数据*/}
                    <Header a={this.a} addTodo={this.addTodo}/>
                    <List todos={todos} updateTodo={this.updateTodo} deleteTodo={this.deleteTodo}/> {/*todos变量在List组件中也要用todos接收*/}
                    <Footer todos={todos} checkAllTodo={this.checkAllTodo} clearAllDone={this.clearAllDone}/>
                </div>
            </div>
        );
    }
}
//暴露App组件
export default App;
