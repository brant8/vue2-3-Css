## XML笔记
**属性**
- id属性值(尽量)唯一
		- `<student id = '1'></student>`

**文本**
- CDATA区：再该区域中的数据会被原样展示
	- 格式：`<![CDATA[ 数据 ]]>`

约束：规定xml文档的书写规则
- 作为框架的使用者（程序员）
	- 能够在xml中引入约束文档
	- 能够简单的读懂约束文档
	
分类：
- DTD：一种简单的约束技术
- Schema：一种复杂的约束技术

DTD：引入dtd文档到xml文档中
- 内部dtd：将约束规则定义在xml文档中
	- xml文档中加入约束
		- `<!DOCTYPE students [ 约束规则 ]>`
- 外部dtd：将约束的规则定义在外部的文档中
	- 本地：`<!DOCTYPE students SYSTEM "student.dtd">`
		- 格式：`<!DOCTYPE 根标签名 SYSTEM "dtd文件的位置">`
	- 网络：`<!DOCTYPE 根标签名 PUBLIC "dtd文件名字" "dtd文件的位置URL">`

`student.dtd`规则文件👇
```
<!ELEMENT students (students*)> //student标签可以出现N次，`+`允许出现1次
<!ELEMENT student (name,age,sex)> //student下可以出现的标签以及只能出现一次
<!ELEMENT name (#PCDATA)> //内容为字符串
<!ELEMENT age (#PCDATA)>
<!ELEMENT sex (#PCDATA)>
<!ATTLIST student number ID #REQUIRED> //声明属性，属性名称为number，类型为ID唯一，必须出现
```

**Schema**：
- 引入：
	- 填写xml文档的根元素
	- 引入xsi前缀。 `xmls:xsi="http://www.w3.org/2001/XMLSchema-instance"`
	- 引入xsd文件命名空间。 `xsi:schemaLocation="http://www.google.ca/xml student.xsd"`
	- 为每一个xsd约束声明一个前缀，作为标识。`xmls="http://www.google.ca/xml"`
	- `<students xmls:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://www.itcast.cn/xml" xsi:schemaLocation="http://www.itcast.cn/xml student.xsd">`

**解析**：操作xml文档，将文档中的数据读取到内存中。
**写入**：将内存中的数据保存到xml文档中，持久化的存储。

***

解析xml的方式：
- DOM：将标记语言文档一次性加载到内存中，在内存中形成一颗dom树。
	- 优点：操作方便，可以对文档进行CRUD的所有操作
	- 缺点：占内存
- SAX：逐行读取，基于事件驱动的。
	- 优点：不占内存，读取一行释放一行。
	- 缺点：只能读取，不能增删改查。

xml常见的解析器
- JAXP：sun公司提供的解析器，支持dom和sax两种思想(不推荐)
- DOMJ：一款非常优秀的解析器
- Jsoup：一款Java的HTML解析器。解析某个URL地址，HTML文本内容。
- PULL： Android操作系统内置的解析器，sax方式。

***
Jsoup快速入门👇
- 导入jarb奥
- 获取Document对象
```
//获取student.xml的path
String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
//解析xml文档，加载文档京内存，获取dom树 --->Document
Document document = Jsoup.parse(newFile(path),"utf-8");
//**********以上两行在下面均要用到，下处省略***************//

System.out.println(document);
//获取元素对象Element
Element elements = document.getElementsByTag("name");
System.out.println(element.size());
//获取第一个name的Element对象
Element element = elements.get(0);
//获取数据
String name = element.text();
System.out.println(name);
```
对象的使用
- Jsoup：工具类，可以解析html或xml文档，返回Document
	- parse()方法：解析html或xml文档，返回Document
		- parse(file in, String charsetName)：解析xml或html文件。
		- parse(String html)：解析xml或html字符串。
		- parse(URL url, int timeoutMillis)：通过网络路径获取指定的html或xml的文档对象。
- Document：文档对象，代表内存中的dom树
	- 获取Element对象（*继承自Element*）返回均是Elements/Element对象
		- getElementsByTag(String tagName)：根据标签名称获取元素对象集合。
		- getElementsByAttribute(String key)：根据属性名称获取元素对象名称
		- getElementsByAttributeValue(String key, String value)：根据对应的属性名和属性值获取元素对象集合。
		- getElementById(String id)：根据id属性值获取一味的element对象。
```
//通过Document对象获取name标签，获取所有的name标签，获取多个(案例有2个)
Elements elements = document.getElementsByTag("name");
System.out.println(elements.size());
```
- Elements：元素Element对象的集合。可以当作ArrayList<Element>来使用
- Element：元素对象
	- 获取子元素对象->同`Document`
	- 获取属性值
		- `String attr(String key)`：根据属性名称获取属性值
	- 获取文本内容
		- `String text()`：获取所有子标签的纯文本内容
		- `String html()`：获取标签体的所有内容(包括子标签的标签和文本内容)
```
案例：
//通过Element对象获取子标签对象
Element element_stu = document.getElementsByTag("student").get(0);
Elements ele_name = element_stu.getElementsByTag("name");
System.out.println(ele_name.size());
//获取student对象的属性值
String number = element_stu.attr("number");
System.out.print(number);
//获取文本内容
String text = ele_name.text();
String html = ele_name.html();
System.out.print(text);
System.out.print(html);
```
- Node：节点对象(所有上面的爹)


**快速查询方式**
 - selector：选择器
	 - 使用方法 Elements select(String cssQuery)， cssQuery为css选择器
	 - 语法：参考Selector类中定义的语法
```
//查询name标签，如css中的div{}
Elements elements = document.select("name");
System.out.println(elements);
//获取id标签带值有为itcase的
Elements elements_id = document.select("#itcase");
System.out.println(elements_id);
//获取student标签并且number属性值为001
Elements element = document.select("student[number=\'001\']")
//获取student标签并且number属性值为001的age子标签
Elements element = document.select("student[number=\'001\'] > age")
```
- XPath：是一门在XML文档中查找信息的语言
	- 使用Jsoup的Xpath需要额外导入jar包
```
//获取student.xml的path
String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
//解析xml文档，加载文档京内存，获取dom树 --->Document
Document document = Jsoup.parse(newFile(path),"utf-8");
//根据document对象，创建JXDocument
JXDocument jxDocument = new JXDocument(document);
//结合xpath语法查询，返回一个集合，查询所有student标签
List<JXnode> jxNodes = jxDocument.selN("//student");
for(JXNode jxNode : jxNodes){
	System.out.println(jxNode);
}
//查询所有student标签下的name标签
List<JXnode> jxNodes = jxDocument.selN("//student/name");
//查询所有student标签下的带有id的属性的name标签
List<JXnode> jxNodes = jxDocument.selN("//student/name[@id]");
//查询所有student标签下的带有id的属性的name标签并且值为itcase
List<JXnode> jxNodes = jxDocument.selN("//student/name[@id='itcase']");
```



