# fre.shown.tryboot

fre.shown.tryboot 用户后台管理系统采用[微服务](https://martinfowler.com/articles/microservices.html)框架搭建，主要技术为spring boot, spring cloud, docker 技术栈。

**本项目正在开发中。**

## 架构划分

| 服务（子项目）名称 |               功能简述               |
| :----------------: | :----------------------------------: |
|       config       | 配置中心：Spring Cloud Config Server |
|      registry      |   注册中心：Netflix Eureka Server    |
|    api-gateway     |          网关：Zuul Client           |
|  account-service   |               账户服务               |
|        cdn         | 独立部署, 为项目静态资源提供CDN服务  |



## 开发过程中的 Q&A

### `Config Server` 和 `Eureka Server` 如何一起配置？

首先要明确架构中 `Eureka Server` 和 `Config Server` 的依赖关系来确定如何一起配置。

道理很简单，两个服务运行必须有先后顺序，所以启动时只能单项依赖。

- 如果 registry 启动时需要从 config 中拉取所需配置，那么应该先启动 config，那么config启动时自然是无法注册到注册中心的。
- 反之，如果config启动时想要注册到注册中心，那么注册中心必须比config先启动，因此注册中心启动时无法从config中拉取配置。

### Fein