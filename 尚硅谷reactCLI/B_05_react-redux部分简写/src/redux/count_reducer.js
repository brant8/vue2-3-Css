/*
* 1.该文件是为创建一个Count组件服务的reducer，reducer的本质就是一个函数。
* 2.reducer函数会接收到两个参数，分别是：之前的状态PreState、动作对象action
* 3.reducer是纯哈桉树
**/
/*----推荐初始化写法----
const initState = 0;
function countReducer(preState=initState,action){...}
* */
export default function countReducer(preState,action){
    if(preState === undefined) preState = 0;
    //console.log(action); 初始化时输出（尾部随机字符）： {type: '@@redux/INITs.t.y.h.e.9'}
    //从action对象中获取：type、data
    const {type,data} = action;
    //根据type决定如何加工数据，一般此处不用if判断
    switch(type){
        case 'increment':
            return preState + data;
        case 'decrement':
            return preState - data;
        default: //相当于初始化的时候赋值，默认赋值'undefined'
            return preState;
    }
}