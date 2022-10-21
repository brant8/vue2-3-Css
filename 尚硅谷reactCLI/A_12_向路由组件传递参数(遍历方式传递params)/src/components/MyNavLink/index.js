import React, {Component} from 'react';
import {NavLink} from "react-router-dom";

class Index extends Component {
    render() {
        console.log(this.props)
        const {to,title} = this.props;
        return (
            <NavLink activeClassName="active" className="list-group-item" to={to}>{this.props.children}</NavLink>
    );
    }
}

export default Index;