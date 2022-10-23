import React, {Component} from 'react';
import './index.css';

export default class Index extends Component {
    render() {
        return (
            <div className="pros">
                <h3>我是Parent组件</h3>
                {/*<A>Hello World - A组件</A>*/}
                {/**    <A render={()=><B/>} />    //此处render能得到A组件this.props.render()的返回值 */}
                {/*A组件的返回值传给B*/}
                <A render={(name)=><B name={name}/>} />
            </div>
        );
    }
}

class A extends Component{
    state={name:'tom'}
    render() {
        return(
            <div className="a">
                <h3>我是A组件</h3>
                {/*需要使用this.props.children，才能在父组件中调用 标签、子组件*/}
                {/*{('A-children',this.props.children)}*/}
                {/*调用从父亲穿过来的render，并且赋值返回给父组件*/}
                {this.props.render(this.state.name)}
                {/*<B name={this.state.name}/>*/}
            </div>
        )
    }
}
class B extends Component{
    render() {
        return(
            <div className="b">
                <h3>我是B组件,{this.props.name}</h3>
            </div>
        )
    }
}