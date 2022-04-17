import {flag,sum} from "./aaa.js";
import {num1,height} from "aaa.js";
import {mul} from "aaa.js";



if(flag){
    console.log('我是天天');
    console.log(sum(20,30));
}

console.log(num1);

console.log(mul(10,20));

//使用默认导出方式导入
import addr from "aaa.js"

//统一全部导入
import * as aaa from "aaa.js";
aaa.sum(30,40);