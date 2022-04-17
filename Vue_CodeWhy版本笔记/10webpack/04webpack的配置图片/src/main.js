//入口js不放在js目录下
const {add,mul} = require('./js/mainUtils.js');

console.log(add(20,30));
console.log(mul(20,30));

import {name,age,height} from "./js/info";

console.log(name)
console.log(age)
console.log(height)

//依赖css文件，需要loader才能处理css文件
require('./css/normal.css')


