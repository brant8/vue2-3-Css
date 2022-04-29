### Nginx
Nginx是一款http服务器、反向代理服务器及电子邮件(IMAP/POP3)代理服务器。静态网站服务器。服务器集群。

安装：
* 需要gcc环境
	* `$ yum install gcc-c++`
* 安装第三方包
	* PCRE（Perl Compatible Regular Expressions）是一个Perl库，包括perl兼容的正则表达式库。nginx的http模块使用pcre来解析正则表达式，所欲需要安装pcre库。
		* `$ yum install -y pcre pcre-devel`  #pcre-devel是二次开发库，也需要。
	* zlib库提供了很多种压缩和解压缩的方式，nginx使用zlib对http包内容进行gzip。
		* `$ yum install -y zlib zlib-devel`
	* OpenSSL是一个强大的安全套接字层密码库，囊括密码算法，常用密钥和证书封装管理功能及SSL协议，并提供丰富的应用型恒旭测试或其他目的使用。支持http协议，https协议。
		* `$ yum install -y openssl openssl-devel`
	* 进入nginx目录，使用configure命令创建makeFile文件
		* `./config \`及参数，可参考网上
```
$ ./configure 
--prefix=/usr/local/nginx
--pid-path=/var/lock/nginx.lock
--error-log-path=/var/log/nginx/error.log
--http-log-path=/var/log/nginx/access.log
--with-http_gzip_static_module
--http-client-body-temp-path=/var/temp/nginx/client  //临时目录创建
--http-proxy-temp-path=/var/temp//nginx/proxy
--http-fastcgi-temp-path=/var/temp/nginx/fastcgi
--http-uswsgi-temp-path=/var/temp/ngix/uwsgi
--http-scgi-temp-path=/var/temp/nginx/scgi

$ make
$ isntall
```
Nginx启动与访问
创建临时目录，配置安装时已指定，只需要创建即可
* `mkdir /var/temp/nginx/client -p`

启动：`/usr/local/nginx/sbin/nginx`
关闭：`./nginx -s stop` 或者`./nginx -s quit`（温和退出）

### 配置虚拟主机
* 上传静态网站（与sbin目录同级别）
	* 将静态页面/index目录上传至/usr/local/nginx/index下
	* 将静态页面/regist目录上传至/usr/local/nginx/regist下
* 修改Nginx配置文件：/usr/local/nginx/conf/nginx.conf

* 通过端口绑定
```
...
Server{
	listen 80; #默认端口号
	server_name localhost; #域名或者ip
	location / {
		root index; #默认访问资源的目录
		index index.html index.htm; #默认访问资源的名称
	}
	error_page 500 502 503 504 /50x.html; #错误页面
	location = /50x.html{
		root html;
	}
	}
Server{
	listen 81; #默认端口号
	server_name localhost; #域名或者ip
	location / {
		root regist; #默认访问资源的目录
		index regist.html; #默认访问资源的名称
	}
	error_page 500 502 503 504 /50x.html; #错误页面
	location = /50x.html{
		root html;
	}
	}
}
```
`$ ./nginx -s reload`

* 通过域名绑定
包含有二级域名三级域名等，访问域名都是先访问本地的hosts文件（本机），如没有则访问DNS服务器。
```
#本地测试，C:\Windows\System32\drivers\etc\hosts
192.168.177.129 www.hmtravel.com
192.168.177.128 regis.hmtravel.com
```
```
...
Server{
	listen 80; #默认端口号
	server_name www.hmtravel.com; #域名或者ip
	location / {
		root index; #默认访问资源的目录
		index index.html index.htm; #默认访问资源的名称
	}
	error_page 500 502 503 504 /50x.html; #错误页面
	location = /50x.html{
		root html;
	}
	}
Server{
	listen 80; #默认端口号
	server_name regis.hmtravel.com; #域名或者ip
	location / {
		root regist; #默认访问资源的目录
		index regist.html; #默认访问资源的名称
	}
	error_page 500 502 503 504 /50x.html; #错误页面
	location = /50x.html{
		root html;
	}
	}
}
```
`$ ./nginx -s reload`

### 代理（反向）
准备工作：
* 把traverl案例部署到tomcat的root目录中
* 启动Tomcat，输入http://192.168.177.128:8080可以看到网站首页

配置反向代理：在Nginx主机修改配置文件
```
...
upstream tomcat-travel{ //与Server同级别
	server 192.168.177.129:8080； #被代理ip和端口
}
Server{
	listen 80; #默认端口号
	server_name www.hmtravel.com; #域名或者ip
	location / {
		proxy_pass http://tomcat-travel; #来自upstream
		index regist.html; #默认访问资源的名称
	}
	error_page 500 502 503 504 /50x.html; #错误页面
	location = /50x.html{
		root html;
	}
	}

```
## 负载均衡
步骤：
* 复制多个tomcat目录，tomcat1, tomcat2, tomcat3。每个目录包含travel项目。
* 编辑`tomcatX/conf/server.xml`
```
#更改所有的port端口号，比如所有端口+1
<Server port="8005" shutdown="SHUTDOWN">
<Connector port="8081" protocol="8444" connectionTimout="2000" redirectPort="8444"/>
...
```
* 逐个开启tomcat服务器`tomcatX/bin/startup.sh`
* 配置`/usr/local/nginx/conf/nginx.confg`
```
#添加upstream 
upstream tomcat-travel{
	server 192.168.177.129:8080 weight=2; #默认值1.数字高访问优先高，比如2为占比50%访问。
	server 192.168.177.129:8081;
	server 192.168.177.129:8082;
}

Server{
	listen 80; #默认端口号
	server_name www.hmtravel.com; #域名或者ip
	location / {
		proxy_pass http://tomcat-travel; #来自upstream
		index regist.html; #默认访问资源的名称
	}
	error_page 500 502 503 504 /50x.html; #错误页面
	location = /50x.html{
		root html;
	}
	}

./nginx reload
```

