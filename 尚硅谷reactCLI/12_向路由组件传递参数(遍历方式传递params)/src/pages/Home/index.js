import React, {Component} from 'react';
import {Route} from 'react-router-dom';
import MyNavLink from '../../components/MyNavLink'
import News from './News';
import Message from './Message';

class Index extends Component {
    render() {
        return (
            <div>
                <h3>Home</h3>
                <div>
                    <ul className="nav nav-tabs">
                        <li>
                            <MyNavLink to='/home/news'>News</MyNavLink>
                        </li>
                        <li>
                            <MyNavLink to='/home/message'>Message</MyNavLink>
                        </li>
                    </ul>
                    {/*注册路由*/}
                    <Route path="/home/news" component={News}/>
                    <Route path="/home/message" component={Message}/>
                </div>
            </div>
        );
    }
}

export default Index;