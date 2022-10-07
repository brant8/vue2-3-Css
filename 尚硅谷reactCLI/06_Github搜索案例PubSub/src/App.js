import React, {Component} from 'react';
import Search from './components/Search'
import List from './components/List'

class App extends Component {

    render() {
        return (
            <div id="app">
                <div className="container">
                    <Search/>
                    <List/>
                </div>
            </div>
        );
    }
}

export default App;