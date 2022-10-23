import React, {Component} from 'react';
import ReactDOM from "react-dom/client";
import {root} from '../../index'

//类式组件
class Index extends Component {
    state = {count:0}
    add=()=>{
        this.setState(state=>({count:state.count+1}))
    }
    componentDidMount() {
        this.timer = setInterval(()=>{
            this.setState( state=>({count:state.count+1}))
        },1000)
    }
    //卸载组件
    unmountComp=()=>{
        // const root = ReactDOM.createRoot(document.getElementById('root'));
        root.unmount();
    }
    //卸载组件之前要停止计时器，否则组件卸载，但是计时器还在运行会报错
    componentWillUnmount() {
        clearInterval(this.timer)
    }
    myRef = React.createRef()
    show = ()=>{
        console.log(this.myRef.current.value);
    }
    render() {
        return (
            <div>
                <h2>当前求和为：{this.state.count}</h2>
                <input type="text" ref={this.myRef}/> <br/>
                <button onClick={this.add}>点击加一</button>
                <button onClick={this.unmountComp}>点击卸载组件</button>
                <button onClick={this.show}>点击显示数据</button>
            </div>
        );
    }
}
//函数式组件
function Demo(){

    const [count,setCount] = React.useState(0);
    //加的回调
    function add(){
        setCount(count+1);
    }
    React.useEffect(()=>{
        //操作内容相当于componentDidMount
        let timer = setInterval(()=>{
            setCount(count=>count+1)
        },1000);
        //return返回内容相当于unmountComponentAtNode
        return ()=>{
            clearInterval(timer)
        }
    },[])

    //卸载组件的回调
    function removeComp(){
        const root = ReactDOM.createRoot(document.getElementById('root'));
        root.unmount();
    }

    const myRef = React.useRef();
    function show(){
        console.log(myRef.current.value)
    }
    return (
        <div>
            <h2>当前求和为：{count}</h2>
            <input type="text" ref={myRef}/>
            <button onClick={add}>点击加一</button>
            <button onClick={removeComp}>点击卸载组件</button>
            <button onClick={show}>点击提示输入数据</button>
        </div>
    );
}

export default Demo;