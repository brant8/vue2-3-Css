### 注意事项
避坑：
* ![](Spring&SpringMVC&Mybatis%E6%95%B4%E5%90%88%E5%8F%8A%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9_md_files/image_20211006231852.png?v=1&type=image&token=V1:aPYYusJ33PlGsLg3Zpe3zC2qXmOqGxuv0OWVQDX7kk0)
* ![](Spring&SpringMVC&Mybatis%E6%95%B4%E5%90%88%E5%8F%8A%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9_md_files/image_20211006232254.png?v=1&type=image&token=V1:oN7kdF4IDOq4c8Dlv6x4-ImUDY2o91SubRscz0yjxqo)
* ![](Spring&SpringMVC&Mybatis%E6%95%B4%E5%90%88%E5%8F%8A%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9_md_files/image_20211006232713.png?v=1&type=image&token=V1:vESK9SALOALsIUVk5HxYegUaFJuADNwYB5-qnL30jTc)
* `applicationContext.xml`和mybatis的`sqlMapConfig.xml`均能配置数据源jdbc
* 

![](Spring&SpringMVC&Mybatis%E6%95%B4%E5%90%88%E5%8F%8A%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9_md_files/image_20211006233406.png?v=1&type=image&token=V1:nbBPUaLUYz6LilDC961WLgtqflN-kuKzI51CRo_MDig)
整合：applicationContext.xml的数据源配置，让Spring容器创建数据源等赋予mybatis使用。
![](Spring&SpringMVC&Mybatis%E6%95%B4%E5%90%88%E5%8F%8A%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9_md_files/image_20211006235555.png?v=1&type=image&token=V1:VkobRorwDN2lT4mX0-sxD8gSrw0gU3iMuA-ljEXs03E)
然后在ServiceImple使用注入的mapper的实现，注入前/后对比：
![](Spring&SpringMVC&Mybatis%E6%95%B4%E5%90%88%E5%8F%8A%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9_md_files/image_20211007011513.png?v=1&type=image&token=V1:VMRf4eAm2KzHXcGNxVCh0VplggOwtRuvjJ04_aGPGyk)
sqlMapConfig与sqlMapConfig-spring前/后对比：
![](Spring&SpringMVC&Mybatis%E6%95%B4%E5%90%88%E5%8F%8A%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9_md_files/image_20211007011856.png?v=1&type=image&token=V1:j6b8AxeQTnMwpQCWB3vqQJWeNab6uxp9rR7zsAANEus)

applicationContext前（右）后（左）对比：
![](Spring&SpringMVC&Mybatis%E6%95%B4%E5%90%88%E5%8F%8A%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9_md_files/image_20211007012312.png?v=1&type=image&token=V1:ZD35b1Uobxjs4G1_aJXl-q-frLAjMoHv8X4fgGcCre4)


不整合：上述代码在context.xml不写，在sqlMapConfig.xml写入数据源等，如下
![](Spring&SpringMVC&Mybatis%E6%95%B4%E5%90%88%E5%8F%8A%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9_md_files/image_20211006235914.png?v=1&type=image&token=V1:SmfULRAv5cH1X8AsZ2nPFowxStYegYLLnW2XNuVlfJM)
Mybatis的xml使用dtd格式开头
```
<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE configuration  
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"  
 "http://mybatis.org/dtd/mybatis-3-config.dtd">
 <configuration>
 。。。
 </configuration>
```






