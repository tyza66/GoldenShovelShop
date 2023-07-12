# GoldenShovelShop
### 金铲子商店
- 一个简单的*Spring Cloud Alibaba全家桶*微服务项目
- 实现充值式商城，用户可以通过充值金币来购买商品
- 实现用户、商品、订单、支付、库存、积分、优惠券等模块
- 实现商品评论区，用户可以对商品进行评论
- 分布式后端，完全前后端分离，网关解决跨域

##### 设计图

##### 使用技术
环境：Java8、Node、React、MySQL、Nacos、Docker、RocketMQ、Sentinel、Seata、Redis
后端技术栈：Spring、Spring Boot、Spring Cloud、Spring Cloud Alibaba、Junit、Lombok、Sa-Token系列、Knife4j、MyBatis、MyBatis Plus、RocketMQ、Hutool、Sentinel、Seata、Jackson、Dubbo、Lettuce、AliPay-Easy-Sdk、
前端技术栈：React、Ant Design、Axios、React Router

##### Nacos注册中心
- 使用MySql数据库实现配置持久化
- 使用Nacos注册中心管理全局的服务和网关

##### 服务端口列表
- Nacos(注册中心)：8848
- Sentinel(流控降级)：9091
- React(前端页面)：3000
- GSS-Gateway(Gateway网关)：9090
- GSS-User(用户服务)：9601
- GSS-Goods(商品服务)：9602
- GSS-Recharge(充值服务)：9603


##### 文件对应列表
- 微服务后端：GoldenShovelShop-SC
- React-AntD前端：goldenshovelshop-ad
- 文档：文档

##### 本地复现

##### Q&A

##### 用户权限代码表
- 普通用户：0
- 管理员：1

##### 返回code表
- 接口限流（未处理）：100
- 服务降级（未处理）：101
- 热点参数限流（未处理）：102
- 触发系统保护规则（未处理）：103
- 授权规则不通过（未处理）：104
- 成功：200
- 权限不足：201
- 失败：202
- 错误：203

By：tyza66