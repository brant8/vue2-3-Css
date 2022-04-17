let name='夏明';
var age=18;
var flag = true;

function sum(num1, num2){
    return num1 + num2;
}
if(flag){
    console.log(sum(20,30));
}


export{ //1.到处方式一
    flag,
    sum,
}

//2.导出方式二
export var num1 = 1000;
export var height = 12;

//3.导出函数/类
export function mul(num1,num2){
    return num1+num2;
}
export class Person{
    run(){
        console.log("ruunning...");
    }
}

//4.export default,只能有一个default
const address="北京";
export default address;


