import {NavLink,Routes,Route,Navigate,useRoutes} from "react-router-dom";
import routes from './routes'

export default function App() {

  function computedClassName({isActive}){
    return isActive ? 'list-group-item atguigu' : 'list-group-item'
  }
  const elements = useRoutes(routes);

  return (
    <div className="App">
      <div>
        <div className="row">
          <div className="col-xs-offset-2 col-xs-8">
            <div className="page-header"><h2>React Router Demo</h2></div>
          </div>
        </div>
        <div className="row">
          <div className="col-xs-2 col-xs-offset-2">
            <div className="list-group">
              <NavLink className={computedClassName} to="/about">About</NavLink><br/>
              <NavLink className={computedClassName } to="/home">Home</NavLink>
            </div>
          </div>
          <div className="col-xs-6">
            <div className="panel">
              <div className="panel-body">
                {/*注册路由*/}
                {elements}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

