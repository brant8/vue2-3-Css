import React, {Component,lazy,Suspense} from 'react';
import {Link,Route,Routes} from 'react-router-dom';
const Home = lazy(()=> import('./Home') )
const About = lazy( ()=>import('./About') )

class Index extends Component {
    render() {
        return (
            <div>
                <h1>懒加载</h1>
                <ul>
                    {/*路由*/}
                    <li><Link to="/about">About</Link></li>
                    <li><Link to="/home">Home</Link></li>
                </ul>
                {/*注册路由*/}
                <div>
                    <Suspense fallback={<h1>加载中...</h1>}>
                        <Routes>
                            <Route path="/about" element={<About/>}/>
                            <Route path="/home" element={<Home/>}/>
                        </Routes>
                    </Suspense>
                </div>
            </div>
        );
    }
}

export default Index;