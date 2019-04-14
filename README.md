# tryboot

tryboot 项目采用[微服务](https://martinfowler.com/articles/microservices.html)框架搭建，主要技术为spring boot, spring cloud 技术栈。



**本项目正在开发中。**

#### 

## 开发过程记录

### 基础设施

-  搭建集群 eureka 服务器完成
- 搭建认证服务器完成, 使用 Spring cloud security

### 业务

- 完成 account-service 微服务的认证代码, 使用到 feign



## 架构划分

|     服务名      |          功能简述           |  部署端口  | 实例个数 |
| :-------------: | :-------------------------: | :--------: | :------: |
|     config      | Spring Cloud Config配置中心 |    8888    |    1     |
|    registry     |     集群 Eureka注册中心     | 8762, 8763 |    2     |
|   api-gateway   |          Zuul网关           |     80     |    1     |
| account-service |        用户信息服务         |    8600    |    1     |
|  auth-service   |          认证服务           |    5000    |    1     |
|       cdn       | 为静态资源提供CDN, 独立部署 |    8081    |    1     |









