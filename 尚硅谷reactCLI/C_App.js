import React, {Component} from 'react';
import SetState1 from './components/1_setState'
import LazyLoad2 from './components/2_lazyLoad'
import Hooks3 from './components/3_hooks'
import Fragment4 from './components/4_Fragment'
import Context5 from './components/5_Context'
import Optimize6 from './components/6_optimize'
import RenderProps7 from './components/7_renderProps'
import ErrorBoundary8 from './components/8_errorBoundary/parent'

class App extends Component {
  render() {
    return (
        <div>

            <SetState1 x={100}/>
            <LazyLoad2 />
            <Hooks3/>
            <Fragment4/>
            <Context5/>
            <Optimize6/>
            <RenderProps7/>
            <ErrorBoundary8/>
        </div>
    );
  }
}

export default App;