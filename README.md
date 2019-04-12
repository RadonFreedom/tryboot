# tryboot

tryboot 用户后台管理系统采用[微服务](https://martinfowler.com/articles/microservices.html)框架搭建，主要技术为spring boot, spring cloud 技术栈。

**本项目正在开发中。**



## 架构划分

| 服务（子项目）名称 |               功能简述               |
| :----------------: | :----------------------------------: |
|       config       | 配置中心：Spring Cloud Config Server |
|      registry      |   注册中心：Netflix Eureka Server    |
|    api-gateway     |              网关：Zuul              |
|  account-service   |             用户信息服务             |
|    auth-service    |   认证服务: Spring Security Oauth2   |
|        cdn         | CDN服务: 为静态资源提供CDN, 独立部署 |



## 开发过程中的 Q&A

### `Config Server` 和 `Eureka Server` 如何一起配置？

首先要明确架构中 `Eureka Server` 和 `Config Server` 的依赖关系来确定如何一起配置。

道理很简单，两个服务运行必须有先后顺序，所以启动时只能单向依赖。

- 如果 registry 启动时需要从 config 中拉取所需配置，那么应该先启动 config，那么config启动时自然是无法注册到注册中心的。
- 反之，如果config启动时想要注册到注册中心，那么注册中心必须比config先启动，因此注册中心启动时无法从config中拉取配置。

### 用户认证为什么用Oauth2?

- 根本原因, 与单点系统不同, 微服务之间毕竟是不同源的, 如果只是简单的做了一个SSO, 那么每次用户认证都要使用auth-service, 每个面向用户的服务都要和其频繁交互session信息, auth-service服务肯定顶不住这么多访问. 因此, 其他服务和认证服务之间需要减少交互, Oauth2可以解决这个问题.
- Oauth2还能提供统一的认证接口, 这样不管是本方其他服务, 还是第三方服务, 都可以统一调用这个接口来进行本站的用户认证. 这就减少了开发成本, 不用再管JWT等其他的技术方案.
- 服务端无状态：Token 机制在服务端不需要存储 session 信息，因为 Token 自身包含了所有用户的相关信息。
- 我们可以设置边界服务器或者带认证功能的反向代理服务器，假设所有访问请求都发给它。通过认证后，转发给内部相应的服务器。一般在`Spring MVC Security`开发中几乎都会这样做的。但这并不安全，最重要的是，一旦是有人从内部攻击，你的数据毫无安全性。 Oauth2同样可以解决这个问题, 因为其安全度达到了可以第三方授权的地步, 所以微服务架构下的内部服务之间的安全性有很好的保障.
- 其实访问`Facebook/Google`的敏感数据和访问我们自己后端受保护数据没什么区别，并且他们已经使用这样的解决方案很多年，我们只要遵循这些方法就好了。



