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

