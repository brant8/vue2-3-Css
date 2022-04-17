### 系统命令
`$ ps -ef` 显示系统进程
`$ ps -ef | grep java`根据关键字显示系统进程
`$ kill -9 3101`' -9'为强制关闭进程,3101为进程ID
```
$ service --status-all #查看系统中所有后台服务
$ netstat -nltp #查看系统中网络进程的端口监听情况
#防火墙配置文件/etc/sysconfig/iptablse
#其他版本CentOS service->systemctl
$ service iptables status #查看防火墙状态
$ service iptables stop #关闭防火墙
$ service iptables start #重启防火墙
$ chkconfig iptables off #禁止防火墙自启

#防火墙打开3306端口
$ /sbin/iptables -I INPUT -p tcp --dport 3306 -j ACCEPT
$ /etc/rc.d/init.d/iptables save
$ /etc/init.d/iptables status
```

修改ip地址：
```
$ ifconfig 
$ ifconfig eth0 192.168.0.100 //临时修改ip地址

//永久生效
$ vi /etc/sysconfig/network-scripts/ifcfg-etho0
Device=etho0 #网卡名称
#TYPE=Ethernet
BOOTPROTP=static  #获取ip的方式static/dhcp/bootp/none
#BOOTPROTO=dhcp
HWADDR=00:0C:29:B5:B2:59
IPADDR=192.168.177.129 #ip地址
NETMASK=255.255.255.0 #子网掩码
NETWORK=192.168.177.0 #网络地址
BROADCAST=192.168.0.255 #广播地址
#ONBOOT=no
```

域名映射`/etc/hosts`

***

RPM包：按照redhat包管理工具规范进行发布。
* 安装指定的包，但不会安装该包所需要的依赖的包。如包A依赖于B，RPM安装A并不会安装B，并且会安装失败，需要先安装B，再安装A。

Yum在线安装：按照RPM方式在线安装，但是会自动安装依赖包。

查看当前安装版本: 
`$ rpm -qa | grep java`
卸载：
`$ rpm -e --nodeps java-1.6.0-openjdk-1.6.0.35-1.13.7.1.e16_6.i686`
安装:
`$ rpm -ivh xxx.rpm`  //自定义安装到目录`usr/local/mysql`

解压到当前目录： `$ tar -zxvf xx.tar.gz`
自定义安装Java版本到目录`/usr/local/jdk`
配置Java环境变量：`/etc/profile`添加如下
```
#末尾添加
JAVA_HOME=/usr/local/jdk/jdk1.7.0_71
CLASSPATH=.:$JAVA_HOME/lib.tools.jar
PATH=$JAVA_HOME/bin:$PATH
export JAVA_HOME CLASSPATH PATH
```
环境变量设置完毕重新加载配置文件：`$ source /etc/profile`

给远程机器登录**MySQL**权限：
* `$ grant all privileges on *.* to 'root'@'%' identified by 'root';`
* `$ flush privileges;`
* 如果测试失败关闭防火墙`service iptables stop`
* 实际环境让防火墙跳过Mysql端口

**Tomcat**直接解压运行`/usr/loca/tomcat/startup.sh`

**Redis**是C语言开发，安装需要将源码进行编译，编译依赖`gcc`环境。
```
$ yum install gcc-c++
#在redis目录下
$ make #只编译
$ make PREFIX=/usr/local/redis install #安装并编译
#需要复制源码redis.conf到redis目录下

```
## 部署
修改`druid.properties`字符集：
`url=jdbc:mysql://travel?characterEncoding=utf-8`

CRT软件方便使用上传下载。

