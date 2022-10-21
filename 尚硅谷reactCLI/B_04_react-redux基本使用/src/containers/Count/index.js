//引入Count的UI组件
import CountUI from '../../components/Count'
/*引入react-redux中的store
这里不使用import导入store，需要在App中Count组件当作参数导入，直接使用store的方法dispatch*/
//引入connect用于连接UI组件与redux
import {connect} from 'react-redux'
import {createIncrementAction,createDecrementAction,createIncrementAsyncAction} from '../../redux/count_action'

/*a函数【返回的对象】值作为状态传递给了UI组件（根据模型图【容器与UI通过props传递状态】）的props的{key:value}
*value就作为传递给UI组件props的value---状态
* react-redux调用a函数的时候，state传入到该函数，此函数名为a的，官方使用mapStateToProps*/
function a(state){
    return {count:state};
    /*return 1; ---用于测试错误信息，输出官方方法命名---*/
}
//根据模型图 【UI通过props操作容器状态的方法】
//此函数名为b的，官方使用mapDispatchToProps
function b(dispatch){
    return {
        jia:(number)=>{//注意此处相当于函数名为jia的方法对象
            //通知redux执行加法(通过dispatch(action对象)，操作状态)
            dispatch(createIncrementAction(number));
        },
        jian:(number)=>{ dispatch(createDecrementAction(number))},
        jiaAsync:(number,time)=>{dispatch(createIncrementAsyncAction(number,time));}
    }/*return 1; ---用于测试错误信息，输出官方方法命名---*/
}

//使用connect()()创建并暴露一个Count的容器组件（让compoents和containers的Count产生联系）
export default connect(a,b)(CountUI)


