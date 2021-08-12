# quartz
Code for Quartz Learning
基于springboot 2.3.6.RELEASE + quartz的 CRUD 定制定时任务的项目
## 开发环境
jdk1.8 + maven + idea2020.1 + mysql5.7
## 启动说明
- 项目使用的数据库为MySql5.7，选择resources中的tables_mysql.sql文件初始化数据库信息。
- 在resources/application.properties 以及quartz.properties文件中替换为自己的数据源。
- 运行Start main方法，启动项目。
- 项目swagger访问地址：[swagger](http://localhost:8080/swagger-ui/index.html)

## 已实现功能

- 任务列表分页查询
- 任务新增和修改
- 任务执行
- 任务暂停
- 任务恢复继续
- 任务删除
