import React, {Component} from 'react';
//注意此处用到的是containers，而不是components
import Count from './containers/Count'
import store from './redux/store'

class App extends Component {
    render() {
        return (
            <div>
                {/*给容器组件传递store*/}
                <Count store={store}/>
            </div>
        );
    }
}

export default App;