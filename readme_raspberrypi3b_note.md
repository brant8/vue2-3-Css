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

5. 内网穿透ngrok - StarryFrp

   1. StarryFrp 使用说明：[地址](https://www.iasuna.com/post-65.html) | 登录地址： [地址](https://frp.starryfrp.com/console/AddProxy)

6. 