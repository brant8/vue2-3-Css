/*该文件专门为Count组件生成action对象*/
/*function createIncrementAction(data){
    return {type:'increment',data};
}  有暴露需要，因此需要写下面方式*/
export const createIncrementAction= (data)=>{
    return {type:'increment',data};
}
export const createDecrementAction= data=>({type:'decrement',data});



