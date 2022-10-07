import React, {Component} from 'react';
import {NavLink,Route} from 'react-router-dom'
import About from './pages/About'
import Home from './pages/Home'

class App extends Component {

    render() {
        return (
            <div id="app">
                <div className="row">
                    <div className="col-xs-offset-2 col-xs-8">
                        <div className="page-header"><h2>React Router Demo</h2></div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-xs-2 col-xs-offset-2">
                        <div className="list-group">
                            {/*原生html中，靠<a>跳转不同的页面*/}
                            {/*<a className="list-group-item active" href="./about.html">About</a>*/}
                            {/*<a className="list-group-item" href="./home.html">Home</a>*/}
                            {/*在React中靠路由连接实现切换组件*/}
                            {/*1.<BrowserRouter>  前端路由监听*/}
                                <NavLink activeClassName="active" className="list-group-item" to="/about">About</NavLink> {/*2. <Link>相当于<a>*/}
                                <NavLink activeClassName="active" className="list-group-item" to="/home">Home</NavLink> {/*3. href使用to 替代*/}
                            {/*</BrowserRouter>*/}
                        </div>
                    </div>
                    <div className="col-xs-6">
                        <div className="panel">
                            <div className="panel-body">
                                {/*5.<BrowserRouter>  前端路由监听*/}
                                    {/*4. 注册路由*/}
                                    <Route path='/about' component={About}/>
                                    <Route path='/home' component={Home}/>
                                {/*</BrowserRouter>*/}
                                {/*BrowserRouter出错，页面两个路由器标签，标识两个不同路由器，无法切换内容
                                解决办法一：在当前组件最外层包裹</BrowserRouter>
                                解决办法二：在index.js的APP组件中包裹</BrowserRouter>*/}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;