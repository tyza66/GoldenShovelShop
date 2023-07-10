# GoldenShovelShop
### 金铲子商店
- 一个简单的Spring Cloud Alibaba全家桶微服务项目
- 实现充值式商城，用户可以通过充值金币来购买商品
- 实现用户、商品、订单、支付、库存、积分、优惠券、活动等模块
- 实现商品评论区，用户可以对商品进行评论

##### 设计图

##### 使用技术
环境：Java8、Node、React、MySQL、Nacos、Docker、RocketMQ、Sentinel
后端技术栈：Spring、Spring Boot、Spring Cloud、Spring Cloud Alibaba、Junit、Lombok、Sa-Token、Knife4j、MyBatis、MyBatisPlus、RocketMQ、Hutool、Sentinel
前端技术栈：React、Ant Design、Axios、React Router

##### Nacos注册中心
- 使用MySql数据库实现配置持久化
- 使用Nacos注册中心管理全局的服务和网关

##### 服务端口列表
- Nacos注册中心：8848
- React前端页面：8080
- GSS-Gateway(Gateway网关)：9090
- GSS-User(用户服务)：9601


##### 文件对应列表
- 微服务后端：GoldenShovelShop-SC
- React-AntD前端：goldenshovelshop-ad
- 文档：文档

##### 本地复现

##### Q&A

##### 用户权限代码表
普通用户：0

By：tyza66