import React, {Component} from 'react';

class Child extends Component {
    state={
        users:'abc'
        // [
//     {id:'001',name:'tom',age:18},
//     {id:'002',name:'tim',age:19},
//     {id:'003',name:'lucy',age:20},
// ]
    }
    render() {
        return (
            <div>
                <h3>我是child组件</h3>
                {
                    this.state.users.map((userObj)=>{
                        return <h4 key={userObj.id}>{userObj.name}---{userObj.age}</h4>
                    })
                }
            </div>
        );
    }
}

export default Child;