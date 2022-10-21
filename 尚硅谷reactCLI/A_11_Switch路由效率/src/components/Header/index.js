import React, {Component} from 'react';

class Index extends Component {
    render() {
        console.log("header组件接收到的props是：",this.props);
        return (
            <div className="page-header">
                <h2>React Router Demo</h2>
            </div>
        );
    }
}

export default Index;