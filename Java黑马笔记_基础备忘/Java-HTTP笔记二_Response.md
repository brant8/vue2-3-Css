* HTTP协议：响应消息
* Response对象
* ServletContext对象

**HTTP协议：**
* 请求消息：客户端发送给服务器端的消息
	* 请求行
	* 请求头
	* 请求体
	* 请求空行
* 响应消息：系服务器段发送给客户端的消息
	* 响应行
		* 组成：协议/版本 响应状态码 状态码描述
		* 响应状态码：服务器告诉客户端浏览器本次请求和相应的一个状态
			* 分类：
				* 1xx：服务器接收客户端休息，但没有接收完成，等待一段时间后，发送1xx状态码
				* 2xx：成功。如：`200`
				* 3xx：重定向。代表：`302`(重定向)，304(访问缓存)
				* 4xx：客户端错误。代表：`404`(请求路径没有对应的资源)，405(请求方式没有对应的doXxx方法, doGet/doPost)
				* 5xx：服务器端错误。代表：`500`(服务器内异常)
	* 响应头
		* 格式：[头名称：值]
		* 常见响应头：
			* `Content-Type`：服务器告诉客户端本次响应体数据格式以及编码格式
			* `Content-disposition`：服务器告诉客户端以什么格式打开响应体数据
				* `in-line`：默认值，在当前页面内打开
				* `attachment`：以附件形式打开响应体，文件下载
	* 响应空行
	* 响应体：传输的数据

### Response对象
功能：设置响消息
 * 设置相应行
	 * 格式：`HTTP/1.1 200 OK`
	 * 设置状态码：`setStatus(int sc)`
 * 设置响应头：`setHeader(String name,String value)`
 * 设置响应体：
	 * 使用步骤：
		 * 获取输出流
			 * 字符输出流：`PrintWriter getWriter()`
			 * 字节输出流：`ServletOutputStream getOutputStream()`
		 * 使用输出流，将数据输出到客户端浏览器

**案例**：
* 完成重定向
	* 转发的特点：转发地址栏路径不变、转发只能访问当前服务器下的资源、转发时一次请求、可以使用request对象来共享数据
	* 重定向的特点：地址栏发生变化、重定向可以访问其他站点(服务器)的资源、重定向是两次请求、不能使用request对象来使用共享数据
	* 路径分类：
		* 相对路径：通过相对路径不可以确定唯一资源
			* 不以`/`开头，以`.`开头路径
			* 找到昂前资源和目标资源之间的相对位置关系
		* 绝对路径：通过绝对路径可以确定唯一资源
			* 以`/`开头路径
			* 规则：判断定义的路径是给谁用的？判断请求将来从哪儿出发
				* 给客户的端浏览器使用：需要加虚拟目录(项目的访问路径)
				* 给服务器使用：不需要加虚拟目录
			* 获取虚拟目录：`request.getContextPath()`
```
@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet{
	protected void doPost(HTtpServletRequest request, HttpSErvletResponse response) throws ServletException,IOException{
	System.out.print("demo1....");
	//访问/responseDemo1会自动跳转到/responseDemo2资源
	//设置状态码为302
	response.setStatus(302);
	//设置响应头location
	responsesetHeader("location","/day15/responseDemo2");

	//简单的重定向方法(第二种重定向)
	response.sendRedirect("/day15/responseDemo2");
	}
	...(doGet(){..})
}
```
* 服务器输出字符数据到浏览器
	* 获取流对象之前，设置流的默认编码(手动设置容易与系统编码不一致)
		*  `response.setCharacterEncoding("utf-8")`
	* 告诉浏览器，服务器发送的消息体数据的编码，建议浏览器使用该编码解码 
		* `response.setHeader("content-type","text/html;charset=utf-8")`
	* 获取字符输出流
		* `PrintWriter pw = response.getWriter()`
	* 输出数据(自动关流)
		* `pw.write("hello response")`
* 服务器输出字节数据到浏览器
	* 防乱码：`response.setContentType("text/html;charset="utf-8")`
	* 获取字节输出流
		* `ServletOutputStream sos = response.getOutputStream()`
	* 输出数据
		* `sos.write("hello".getBytes())`
		* `sos.write("你好”。getBytes("utf-8"))`
* 验证码
	* 本质为图片
```
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet{
	protected void doPost(..){
		int width = 100;
		int height = 50;
		//创建一对象，在内存中画图(验证码图片对象)
		BufferedImage image = new BufferedImage(width,height,BufferedImage,TYPE_INTRGB);
		//美化图片
		//填充背景色
		Graphics g= image.getGraphics();//画笔对象
		g.setColor(Color.PINK);//设置画笔yanse
		g.fillRect(o,o,width,height);
	
		String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		//生成随机角标
		Random ran = new Random();
		for(int i=1; i<=4; i++){
			int index = ran.nextInt(str.length());
			//获取字符
			char ch = str.charAt(index);//随机字符
			//写验证码
			g.drawString(ch+"",width/5*i,height/2);
		}
		//画干扰线
		g.setColor(color.GREEN);
		//随机生成坐标点
		for(int i = 0; i<10;i++){
			int x1 = ran.nextindeed（width);
			int y1 = ran.nextindeed（height);
			int x2 = ran.nextindeed（width);
			int y1 = ran.nextindeed（height);
			g.drawLine(x1,y1,x2,y2);
		}
		//画边框
		g.setColor(Color.BLUE);
		g.drawRect(0,0,width-1,height-1);//防止溢出
		
		//将图片输出到页面展示
		ImageIO.write(image,"jpg",responseOutputStream);
	}
}
```

### HTML示例调用验证码
```
<script>
window.onload = function()[
	//获取图片对象
	var img = document.getElementById("checkCode");
	//绑定单击事件
	img.onclick = function(){
		//加时间戳,给问号加不一样参数，否则传递内容一致，浏览器缓存无法更新验证码
		var date = new Date().getTime();
		img.src = “/day15/checkCodeServlet?”+date
	}
}
<script>
<body>
	<img id="checkCode" src="/day15/checkCodeServlet" />
	<a id="change" href"">看不清换一张？</a>
<body>

```


### ServletContext对象
概念：代表整个web应用，可以和程序的容器(服务器)通信。
获取：
* 通过request对象获取
	* `request.getServletContext()`
* 通过HttpServlet获取
	* `this.getServletContext()`
功能：
* 获取MIME类型
	* MIME类型：在互联网通信过程中定义的一种文件数据类型
		* 格式：大类型/小类型 `text/html`，`image/jpeg`
	* 获取：`String getMimeType(String file)`
	```
	示例：
	ServletContext context = this.getServletContext();
	//定义名称
	String filename= "a.jpg";
	//获取MIME类型
	String mimeType = context.getMimeType(filename)；
	System.out.println(mimeType);
	//返回image/jpeg
	```
* 域对象：共享数据
	* `setAttribute(String name, Object value)`
	* `getAttribute(String name)`
	* `removeAttribute(String name)`
	* ServletContext对象范围：所有用户所有的请求数据
* 获取文件的真实(服务器)路径
	```
	ServletContext context = this.getSErvletContext();
	String realPath = context.getRealPath("/b.txt");//web目录下资源访问
	System.out.println(realPath);
	//File file = new File(realPath);
	String c = context.getRealPath("/WEB-INF/c.txt");//WEB-INF目录下的资源访问
	String a = context.getRealPath("/WEB-INF/classes/a.txt");//src目录下的资源访问
	```

***
案例：
文件下载需求：
* 页面显示超链接
	```
	@WebServlet("/downloadServlet")
	public class DownloadServlet extends HttpServlet{
		protected void doGet(...){
			this.doPost(request,response);
		}
		protected void doPost(..){
			//获取请求参数，文件名称
			String filename = request.getParameter("filename");
			//使用字节流加载文件进内存
			//找到文件服务器路径
			ServletContext servletContext = this.getServletContext()
			String realPath = ServletContext.getRealPath("/img/"+filename);
			//用字节流关联
			FileInputStream fis = new FileInputStream(realPath);
			//设置response的响应头
			//设置响应头类型:content-type
			String mimeType = servletContext.getmimeType(filanem);
			response.setHeader("content-type",mimeType);
			
			//解决中文文件名问题
			//获取user-agent请求头
			String agent = request.getHeader("user-agent");
			//使用工具类方法编码文件名即可
			filename = DownloadUtils.getFileName(agent, filename);

			//设置响应头打开方式：content-disposition
			response.setHeader("content-disposition","attachment;filename="+filename);
			//将输入流的数据写到输出流中
			ServletOutputStream sos = response.getoutputStream();
			byte[] buff = new byte[1024*8];
			int len = 0;
			while((len = fis.read(buff)) != -1){
				sos.write(buff,0,len);
			}
			fis.close();
		}
	}
	```
	```
	HTML代码：
	<a href="/day15/downloadServlet?filename=1.jpg">图片</a>
	<a href="/day15/downloadServlet?filename=九尾.jpg">图片</a>
	<a href="/day15/downloadServlet?filename=1.avi">视频</a>
	```
* 点击超链接后弹出下载提示框
* 完成图片文件下载
	* 中文文件问题
		* 解决思路
			* 获取客户端使用的浏览器信息
			* 
```
public class DownloadUtils{
	public static String getFileName(String agent, String filename) throws UnsupportedEncodingException{
		if(agent.containts("MSIE")){
			filename = URLEncoder.encode(filename,"utf-8");
			filename = filename.replace("+"," ");
		}else if(agent.contains("Firefox")){
		//火狐浏览器
		BASE64Encoder base64Encoder = new BASE64Encoder();
		filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
		}else{
		//其他浏览器
		filename = URLEncoder.encode(filename,"utf-8");
		}
		return filename;
	}
}
```
