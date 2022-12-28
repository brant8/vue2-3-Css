# 树莓派使用笔记留档

1. 基础信息

   1. Linux版本：Ubuntu 22.10
   2. 系统自带ssh
      1. 启动命令：`sudo /etc/init.d/ssh start`或`sudo service ssh start`
      2. 查看状态命令：`sudo systemctl status ssh`

2. 装Docker [参考](https://www.runoob.com/docker/docker-container-usage.html)

   1. 查看版本命令：`docker -v` ； 若没有安装，会提示可安装安装选项。
   2. ubuntu版本：`sudo apt install docker.io`
   3. 查看下载来的镜像：`docker images`
   4. 启动已停止运行的容器：`docker start <container ID>`
   5. 停止一个容器：`docker stop <容器 ID>`

3. docker安装lamp - arm版本

   1. 通过网络搜索关键字，在hub.docker.com难搜到arm版本
   2. `docker pull ownyourbits/lamp-arm:latest`

4. docker安装`宝塔面板`：[参考](https://blog.51cto.com/u_13144124/5425619)

   1. 拉取centos镜像：`docker pull centos`

   2. 创建并启动容器：

      ```bash
      > docker run -i -t -d --restart=always --privileged=true --name baota -p 9020:20 -p 9021:21 -p 9080:80 -p 9443:443 -p 9888:888 -p 9999:8888 -v /home/www:/www centos /sbin/init
      #上面命令的意思是创建一个docker容易命名为baota(名称大家可以自定义)让他后台运行，然后将20，21，80，443，888，8888这五个做一个宿主机和容器的端口映射。并且将宿主机的/home/www文件夹映射到docker容器的/www上去(注意：文件目录如果不存在。宿主机和容器会自己创建，无需手动创建)。privileged表示在运行容器的时候，给容器加特权，设置容器有写文件的权限。
      #-i: 以交互模式运行容器，通常与 -t 同时使用；
      #-t: 为容器重新分配一个伪输入终端，通常与 -i 同时使用；
      #-d: 后台运行容器，并返回容器ID；
      #--name="baota": 为容器指定一个名称；
      #-p: 端口映射，格式为：主机(宿主)端口:容器端口, 可以多次使用
      #--privileged=true: 使用该参数，container内的root拥有真正的root权限，比如使用systemctl命令。注意：镜像名最后的‘/sbin/init’不能少，否则无效
      #-v:挂载目录 本机目录:容器目录
      ```

   3. 进入容器：`docker exec -it baota /bin/bash`

   4. 安装宝塔面板：

      ```bash
      > yum install -y wget && wget -O install.sh http://download.bt.cn/install/install_panel.sh && sh install.sh
      #安装过程中有一个提示输入的地方输入 y 然后回车等待安装完成即可
      #或者使用万能安装，查看：https://www.bt.cn/new/download.html
      ```

   5. 打开地址：

      ```bash
      ==================================================================
      Congratulations! Installed successfully!
      ==================================================================
      外网面板地址: http://外网IP:8888/f76e19c8
      内网面板地址: http://172.17.0.2:8888/f76e19c8
      username: gtf2tvgg
      password: 95da76c9
      If you cannot access the panel,
      release the following panel port [8888] in the security group
      若无法访问面板，请检查防火墙/安全组是否有放行面板[8888]端口
      
      #注意：虽然访问地址为;
      #http://XX.XX.XX.XX:8888/fb07504f
      #但是我们要把这里的8888改为前面我们创建容器时做映射的端口，比如我这里就是要访问9999端口才行
      ```

   6. 安装MariaDB Server: 

      1. 安装命令：`yum install mariadb-server`
      2. 开启服务：`systemctl start mariadb.service`
      3. 初始化数据库登录权限：`mysql_secure_installation`

   7. mysql备选安装指南：[地址1](https://www.jianshu.com/p/69e9485c9610) | [地址2](https://blog.51cto.com/cuiyingfeng/4371860)

5. 内网穿透ngrok - StarryFrp

   1. StarryFrp 使用说明：[地址](https://www.iasuna.com/post-65.html) | 登录地址： [地址](https://frp.starryfrp.com/console/AddProxy)

## CentOS 微信小程序

##### CentOS及准备工作

1. CentOS下载地址：[链接](https://wiki.centos.org/SpecialInterestGroup/AltArch/Arm32/RaspberryPi3)

2. 开启CentOS的DHCP

   1. 查看IP命令：`ip add`
   2. 编辑文件：`/etc/sysconfig/network-scripts/ifcfg-ens33`
   3. 更改：`ONBOOT=yes`
   4. 重启网络命令：`systemctl restart network`
   5. 获得IP地址后，可重新进入`ifcfg-ens33`进行编辑设置静态地址
   6. 更改：`BOOTPROTO='static'`
   7. 重启网络

3. 需求，删除`/usr/local/nodejs`

   1. 若安装错误，可使用命令`rm -rf xxx`删除目录及其子目录下的文件

4. 安装wget：`yum install wget`

5. 所需安装程序：

   1. SSH

   2. NodeJS（注意NodeJS版本，推荐使用版本16，否则最新版要求LIBC版本兼容性）

      1. 使用wget下载nodejs Linux Binaries版本
      2. 解压：`xz -d node-v16.5.0-linux-x64.tar.xz`
      3. 得到tar文件后解压：`tar  xvf xxx.tar`
      4. 创建目录：`/usr/local/nodejs`
      5. 把解压后的文件移动到该目录下`mv node-v.16.xx /usr/local/nodejs`
      6. 编辑：`vim /etc/profile`
      7. 添加到最后一行：`export PATH=$PATH:'/usr/local/nodejs/node-v16.5.0-linux-x64/bin'`
      8. 更新：`source /etc/profile`
      9. 若运行`node`命令后需要退出，连续按`ctrl + c`两次或者`Ctrl + D`或者输入`.exit`

   3. Redis

      1. 到官网下载`tar.gz`文档：`wget https://download.redis.io/releases/redis-7.0.7.tar.gz`
      2. 解压：`tar -zxvf xxx.tar.gz`
      3. 把解压目录移动到全局：`mv redis-7.xx /usr/local/`
      4. 编译：`make`
      5. 安装：`make install`
      6. 得到：`INSTALL redis-server`、`INSTALL redis-benchmark`、`INSTALL redis-cli`
      7. 后台运行redis服务，编辑`redis.conf`：`daemonize yes`
      8. 允许远程登录，编辑`redis.conf`：`bind:127.0.0.1`，将其注释掉
         1. Redis服务器可以跨网络访问，修改：` bind:0.0.0.0`
      9. 其他参考配置，如开机启动：[地址](https://blog.csdn.net/J080624/article/details/78281840) 
      10. Redis Windows版本（可执行）：[地址](https://github.com/MicrosoftArchive/redis/releases) 
      11. 查看redis进程：`ps -aux | grep redis`
      12. 结束该进程：`kill -9 [进程号]`
      13. 启动redis服务：`redis-server redis.conf`
      14. 查看防火墙端口：`firewall-cmd --query-port=6379/tcp`
      15. 开启端口：`firewall-cmd --zone=public --add-port=6379/tcp --permanent`
      16. 重启防火墙：`firewall-cmd --reload`firewall-cmd --reload
      17. 远程连接命令（powershell在redis目录下）：`.\redis-cli.exe -h 192.168.0.113 -p 6379`
      18. 测试命令：`set name xiaoli`， 测试失败，因为有密码
      19. 连接后再输入密码：`auth 123456` (输出OK) ；[参考地址](https://www.itxiaoli.cn/archives/redisConnect.html)

   4. Java

      1. 安装命令：`yum install java` ,默认安装jdk8

      2. 可直接使用`java`命令：`java -version`

      3. 还需要安装：`yum install java-devel` ；即可运行`javac Test.java` 得到`.class`文件

         ```shell
         原因：
         Earlier in this tutorial, we clarified the difference between the full JDK environment for development, and the JRE environment for running Java applications. Although OpenJDK is the name of the open source distribution of Java, you have only actually installed the OpenJDK JRE. In order to install the full OpenJDK JDK, you should install the corresponding package with -devel appended onto its name. This is a common convention for development packages for other programming environments, which Java also follows, although the terminology overlaps awkwardly here.
         ```

      4. 配置`/etc/profile`

         ```shell
         export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.352.b08-2.el7_9.x86_64/jre/bin/ #默认安装
         export PATH=$PATH:/root/javaTest	#java文件所在目录
         
         #注：若安装多个版本jdk，可运行下面命令进行切换默认jdk版本
         $ alternatives --config java
         ```

      5. 得到`.class`文件后，运行类文件：`java Test` （注意此处不加`Test.class`）

      6. 若需要手动操作，同NodeJS，下载JDK解压

      7. 编辑`/etc/profile`

         ```shell
         export JAVA_HOME=/usr/local/java/jdk1.8.0_171
         export JRE_HOME=${JAVA_HOME}/jre
         export CLASSPATH=.:${JAVA_HOME}/lib:$JRE_HOME/lib
         export PATH=${JAVA_HOME}/bin:$PATH
         ```

      8. 是环境变量生效：`source /etc/profile`

      9. 添加软链接方式：`ln -s /usr/local/java/jdk1.8.0_171/bin/java /usr/bin/java`

   5. MySQL

      1. 下载MySQL源安装包：`wget http://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm`

      2. 安装MySql源：`yum -y install mysql57-community-release-el7-11.noarch.rpm`

      3. 查看一下安装效果：`yum repolist enabled | grep mysql.*`

      4. 安装MySQL服务器：`yum install mysql-community-server`

         ```shell
         #若出现错误如下
         The GPG keys listed for the "MySQL 5.7 Community Server" repository are already installed but they are not correct for this package. Check that the correct key URLs are configured for this repository.
         #命令行输入：
         $ rpm --import https://repo.mysql.com/RPM-GPG-KEY-mysql-2022
         ```

      5. 启动MySQL服务：`systemctl start mysqld.service`

      6. 运行一下命令查看一下运行状态 ：`systemctl status mysqld.service`

      7. 初始化数据库密码，查看一下初始密码：`grep "password" /var/log/mysqld.log`

      8. 登录：`mysql -uroot -p`

      9. 修改密码：`ALTER USER 'root'@'localhost' IDENTIFIED BY '****************';`

      10. 其他配置参考地址：[链接](https://cloud.tencent.com/developer/article/1389941)

      11. 可降低密码长度：`set global validate_password_length=6;`

      12. 允许远程访问：`grant all privileges on *.* to 'root'@'%' identified by '123456' with grant option;`

          ```shell
          远程连接数据库的时候需要输入用户名和密码
          用户名：root
          密码:123456
          指点ip:%代表所有Ip,此处也可以输入Ip来指定Ip
          ```

      13. 刷新授权：`FLUSH PRIVILEGES;`

      14. 设置自动启动：`systemctl enable mysqld`和`systemctl daemon-reload`

      15. 防火墙设置方法一，关闭防火墙：`systemctl stop firewalld`

      16. 防火墙设置方法二，开放3306端口：`firewall-cmd --add-port=3306/tcp --permanent `

          1. 注：需要重启防火墙：`systemctl restart firewalld `

      17. 其他可选：设置默认字符集，编辑`/etc/my.cnf`

          1. `[mysqld]`节点增加以下代码：

             ```shell
             [mysqld]
             character_set_server=utf8
             init-connect='SET NAMES utf8'
             ```

          2. 若失败，参考[网上](https://stackoverflow.com/questions/3513773/change-mysql-default-character-set-to-utf-8-in-my-cnf)

             ```shell
             [mysqld]
             default-character-set = utf8
             ```

      18. 不同Linux系统的防火墙参考：[地址](https://lishuma.com/archives/3634)

   6. 























