//创建外壳组件App
import React from 'react';
import Hello from './components/Hello/Hello'

class App extends React.Component{
    render(){
        return (
            <div>
                <Hello/>
            </div>
        );
    }
}
//暴露App组件
export default App;
