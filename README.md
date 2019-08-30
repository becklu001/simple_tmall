# simple_tmall
a simple tmall like web project

[1]新建maven项目，打包方式为war
[2]新建pom.xml文件-- 可以套用模板，必要组件为 parent，web，嵌入tomcat，热部署devtools，
xml解析xmlns.apis，jpa持久化data-jpa，test，junit，thymleaf，jsp，mysql，commons-langs
[3]建包
[4]已建好数据库：jdbc:mysql://127.0.0.1:3306/tmall_springboot?characterEncoding=UTF-8
root/root
[5]设计分类管理的页面资源 listCategory.html
其中采用了 thymleaf技术-- 包含四个嵌入页面 adminHeader，adminNavigator，adminPage，adminFooter
[6]路径的页面映射（包括直接地址栏输入 和 超链接a标签跳转）
(1)"/admin"-->"redirect:admin_category_list"
(2)"/admin_category_list"--->"admin/listCategory"(templates/admin/listCategory.html)
(3):href="'admin_category_edit?id=' + bean.id "--->(templates/admin/editCategory.html)
(4):href="'admin_property_list?cid='+bean.id"--->(templates/admin/listProperty)
(5):href="'admin_product_list?cid=' + bean.id "--->(templates/admin/listProduct)
------url的格式要按照REST标准和需要设计，比如跳转到 listProperty页面需要参数 category.id,形参为cid-------
------格式为?cid=XX-----------

[7]listCategory页面上的数据交互请求路径
(1)list(start)：get(url),url="categories?start="+start;需要查询表category返回分页格式数据，默认显示start对应的page
(2)add(bean):post(url,bean),url="categories" ;需要往category表中持久化数据


===============================================================
第二部分后台：属性管理
【1】数据库表：property(id,cid,name) 外键为cid--->category表（多对一）
【2】前端页面：listProperty 和 editProperty
【3】入口：listCategory.html :href="'admin_property_list?cid'=bean.id" --->"admin/listProperty"
【4】页面映射：
(1):href="'admin_property_edit?id=' + bean.id ---> "admin/editProperty"
【5】前端页面接口：
(1)get(url);url =  "categories/"+cid+"/"+"properties"+"?start="+start;
(2)post(url,this.bean);url="properties"
(3)delete(url);url="properties/"+id
(4)put(url,vue.bean);url="properties"

================================================================
第三部分后台：产品管理
【1】数据库表：product(id,name,subTitle,originalPrice,promotePrice,stock,cid,createDate)
【2】前端页面：listProduct，editProduct
【3】入口 listCategory.html :href="'admin_product_list?cid'=bean.id" --->"admin/listProduct"
【4】前端页面超链接：
（1）:href="'admin_productImage_list?pid=' + bean.id "--- 
（2）:href="'admin_propertyValue_edit?pid=' + bean.id "---
（3）:href="'admin_product_edit?id=' + bean.id "---
【5】前端页面接口：

=================================================================
第三部分后台：产品属性值管理
【1】数据库表：propertyValue(id,pid,ptid,value)
产品属性表（pv表）比较特殊，因为它和两张表同时关联，product表（p表） 和 property表（pt表）
pid 和 ptid在 p表和 pt表中分别为外键
(1)category 表和 product表是 1对多，category表和 property表也是1对多
（2）product和property表（通过category表）是多对多（m:n）
(3)考虑对pv表的crud，查和改都比较好进行，最怕就是 增加 和 删除，由于cid同时作为p表和pt表的外键
	如果 insert into pv values (null,1,201,"pv1");
	其中 pid=1 对应的 cid = 1，而ptid=201 对应的 cid = 2，应该会造成冲突
	----逻辑上是如此的，cid=2 的类才具有的pt，不应改cid=1的product具有-----
	----事实证明了，这样的冲突并不会被检测出来-----所以，不能靠外键约束来制约这里的逻辑冲突----
	
	
	
（4）所能做的只是 限制直接往pv表中插入记录，而是先读取，后修改
	select * from property where cid=(select cid from product where name="xxx")
	读取出 产品名为xxx的产品的 所有属性，但是该sql如何在jpa中实现

	先读取product对应的category，根据category取出 property列表
	然后在propertyvalue表中通过jpa getByProductAndProperty，如果获得的pv为空，那么说明该propertyvalue记录
	还不存在，可以初始化，并且保存到pv表中

【2】href="admin_propertyValue_list?pid="
