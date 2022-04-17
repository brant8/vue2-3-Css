### Maven
maven下载后，在Windows下配置系统环境变量。
* `Advanced - Environment Variables - System variables`
* `New - Variable name【MAVEN_HOME】 - Variable value【Maven根目录】`
* `Path - New -【%MAVEN_HOME%\bin】`

Maven环境配置依赖于Java，需要在系统也配置Java环境变量。

仓库分三类：
*	本地仓库
*	远程仓库(私服，同个局域网)
*	中央仓库(网络)
```
//Default${user.home}/ 【.m2/repository】本地目录难找，可以用下面自定义目录
//<localRepository>/path/to/repo</localRepository>
<localRepository>C:\my_java_maven_respository</localRepository>
```
一个项目包含：
* 核心代码部分
* 配置文件部分
* 测试代码部分
* 测试配置文件

传统项目：
* src目录
* config
* resources

maven项目标准目录结构
* src/main/java核心代码部分
* src/main/resources配置文件部分
* src/test/java目录，测试代码部分
* src/test/resources测试配置文件
* src/main/webapp（JavaWeb额外） 页面资源，js，css，图片等

#### CMD命令：
* `mvn clean`：从他人手里拿到项目，环境不同需要把已编译compile的文件信息清除重新编译。
* `mvn compile`：编译后会生成一个`target`目录，生成`.class文件`。
* `mvn test`：测试命令，在target目录下比`compile`多生成一个`test-classes`目录。
* `mvn package`：生成目录在`target`目录下，既有`classes`目录也有`test-classes`目录。多一个`.war`打包。`pop.xml`文件中`<packaging>war</packaging>`有配置。
* `mvn install`：同`package`命令，并且把包安装到本地的repository仓库。
* `mvn deploy`：发布命令之前需要配置文件，其他命令可以直接执行。

***
Intellij IDEA配置（默认已有配置）：
* Settings **->** Build,Execution,Deployment **->** Build Tools **->** Maven **->** Maven home directory [Maven目录] **->** User settings file [\conf\settings.xm]
* Maven -> Runner -> VM Options: [-DarchetypeCatalog=internal]

添加依赖jar包：`pom.xml`
Maven依赖总仓库地址：https://mvnrepository.com/
```
//格式
<dependencies>
	<dependency>
		<groupId>junit</groupId>  
		<artifactId>junit</artifactId>  
		<version>RELEASE</version>  
		<scope>compile</scope> //作用域
	</dependency>
</dependencies>
```
`<scope>provided</scope>`作用域，指在有依赖jar包和本地配置jar包同时引入导致冲突时，只在写代码的时候起作用，在项目部署后用本地配置jar包。

***
在原来的tomcat6基础上换tomcat7的操作如下：
```
<build>
	<plugins>
		<plugin>
			<groupId>org.apache.tomcat.maven</groupId>
			<artifactId>tomcat7-maven-plugin</artifactId>
			<version>2.2</version>
			<configuration>
				<port>8888</port> //假如tomcat6端口是8080，现在tomcat7端口为8888，tomcat6端口还有效
			</configuration>
		</plugin>
	</plugins>
</build>
```
测试端口在IntelliJ IDEA的最右边的`Mave Projects`滚轴旁可以用`Execute Maven Goal`的`Command line`来输入`tomcat6:run`或者`tomcat7:run`测试端口
