import React, {Component} from 'react';
import {Route,Switch} from 'react-router-dom'
import About from './pages/About'
import Home from './pages/Home'
import MyNavLink from './components/MyNavLink'
import Test from './pages/Test'

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

                                <MyNavLink to="/about" >About2</MyNavLink> {/*2. <Link>相当于<a>*/}
                                <MyNavLink to="/home" >Home</MyNavLink> {/*3. href使用to 替代*/}

                        </div>
                    </div>
                    <div className="col-xs-6">
                        <div className="panel">
                            <div className="panel-body">

                                <Switch>
                                    <Route path='/about' component={About}/>
                                    <Route path='/home' component={Home}/>
                                    <Route path='/home' component={Test}/>
                                </Switch>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;