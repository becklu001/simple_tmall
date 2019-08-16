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
[6]请求路径（url）页面映射
"admin"-->"redirect:admin_category_list"
“admin_category_list”--->listCategory.html
[7]listCategory页面上的数据交互请求路径
(1)admin_property_list?cid=' + bean.id  --> listProperty()
(2)admin_product_list?cid=' + bean.id -->listProduct()
(3)admin_category_edit?id=' + bean.id --> editCategory()
