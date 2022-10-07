import React, {Component} from 'react';

class Index extends Component {
    render() {
        console.log("About组件收到的props是：",this.props)
        return (
            <div>
                <h3>about</h3>
            </div>
        );
    }
}

export default Index;