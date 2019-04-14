# tryboot

tryboot 项目采用[微服务架构 (microservice)](https://martinfowler.com/articles/microservices.html) 搭建，使用 spring boot, spring cloud 技术栈。

本项目旨在提供一个快速的微服务架构开发起点。



## 架构划分

|     服务名      |                     功能简述                     |  部署端口  | 实例个数 |
| :-------------: | :----------------------------------------------: | :--------: | :------: |
|    registry     |                Eureka注册中心集群                | 8762, 8763 |    2     |
|  auth-service   | /home/radon/Downloads/未命名文件 (1).png认证服务 |    5000    |    1     |
|   api-gateway   |                     Zuul网关                     |     80     |    1     |
| account-service |                   用户信息服务                   |    8600    |    1     |
|     config      |           Spring Cloud Config配置中心            |    8888    |    1     |
|       cdn       |           为静态资源提供CDN, 独立部署            |    8081    |    1     |

示意图如下所示: 

![1555254814581](images/README/1555254814581.png)



## 开发过程记录

### 搭建 eureka 服务器集群

本项目中搭建了两个 eureka 实例, 与zookeeper 不同的是 eureka 注重最终一致性.

实例注册表的最终一致性主要靠实例之间的心跳包发送和时间戳机制实现.

配置注意点如下:

```YML
spring:
  application:
  # 注意到不同实例的服务名相同, 实例名不同, 这才体现所谓"集群"
    name: registry

eureka:
  instance:
    prefer-ip-address: true
    hostname: registry*
  client:
    service-url:
    # service-url是Map: private Map<String, String> serviceUrl = new HashMap<>();
    # defaultZone意义是, 这个实例是在defaultZone这个zone中
    # 而默认情况下defaultZone中的值为http://127.0.0.1:8761/eureka/
    # 发现其他不同zone的实例不会加入自己的对等节点集合(体现在PeerEurekaNodes)
    # 因此不会同步
      defaultZone: http://127.0.0.1:8762/eureka/,http://127.0.0.1:8763/eureka/

```



### 搭建oatuh2认证服务器

使用默认的 oauth2 token 实现, 随机的 token 作为 key 值被存储在 Redis 中, value 存储的是对应这个 token 的认证信息.

不使用 JWT token 的原因是:

- 考虑到token安全性 (JWT自身携带认证信息) 和代码工作量 (JWT 需要考虑到信息加密) .
- 在用户量和微服务数量较低时, 依赖于Redis 的 token 存储不会产生很大内存消耗.



#### 基于`application.yml`的客户端认证信息配置方法

主要使用了`@ConfigurationProperties(prefix = "security.oauth2.server")`来读取`application.yml`配置, 将所有配置依次提取并加入配置.

配置标准与`security.oauth2.client`系配置标准完全相同.



#### 基于 Redis 的 token 存储

1. 加入`spring-boot-starter-data-redis`依赖, 默认连接本机端口6379.
2. 配置使用 `RedisTokenStore` 来存储token.

```java
//Oauth2ServerConfig.java

	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore)
                //注入authenticationManager来支持 password grant type
                .authenticationManager(authenticationManager);
    }
    
    @Configuration
    public static class TokenStoreConfig {
		//在开启jwt模式时不使用RedisTokenStore
        @ConditionalOnProperty(name = "security.oauth2.server.enable-jwt-token", havingValue = "false")
        @Bean
        @Autowired
        public TokenStore redisTokenStore(RedisConnectionFactory redisConnectionFactory) {
            return new RedisTokenStore(redisConnectionFactory);
        }
    }
```



### 服务端的认证

当微服务使用 Feign 调用其他微服务时, 需要首先向认证服务器获取token.

Feign 是基于`RestTemplate` 的声明式RESTful Http API (用户提供接口, Feign使用JDK动态代理调用`RestTemplate` 发起请求并封装响应, 类似于Mybatis) .

使用`OAuth2FeignRequestInterceptor`, 在Feign请求发起前进行拦截, 从上下文(`OAuth2ClientContext`)中或者认证服务器获取 token. 

```JAVA
    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), 
        clientCredentialsResourceDetails());
    }
```

`RequestInterceptor`原理如下:

```JAVA
  Request targetRequest(RequestTemplate template) {
    for (RequestInterceptor interceptor : requestInterceptors) {
      interceptor.apply(template);
    }
    return target.apply(template);
  }
```



### TODO Zuul 网关搭建



### TODO Web端认证



## 问题记录

### 原始类型与包装类型造成的 NPE

在[迭代加载POJO中的配置过程](#基于`application.yml`的客户端认证信息配置方法)中, 出现`java.lang.NullPointerException`

```JAVA
@Override
public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

    //基于YML配置, 请在配置文件中添加相关配置
    InMemoryClientDetailsServiceBuilder inMemoryClientDetailsServiceBuilder = clients.inMemory();

    for (Oauth2ServerClientsProperties.Oauth2ClientProperties client : oauth2ServerClientsProperties.getClients().values()) {
        ClientDetailsServiceBuilder.ClientBuilder builder = inMemoryClientDetailsServiceBuilder
                .withClient(client.getClientId())
                .authorizedGrantTypes(client.getAuthorizedGrantTypes())
                .authorities(client.getAuthorities())
                .scopes(client.getScopes())
                .autoApprove(client.getAutoApproveScopes())
                .autoApprove(client.isAutoApprove())
                .secret(client.getSecret())
                .redirectUris(client.getRedirectUris())
                .resourceIds(client.getResourceIds());

        //由于配置方法的参数是原始类型, 必须进行非空校验再传入
        if (client.getAccessTokenValiditySeconds() != null) {
            builder.accessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
        }
        if (client.getRefreshTokenValiditySeconds() != null) {
            builder.refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());
        }
    }
}
```

配置方法源码:

```JAVA
		public ClientBuilder accessTokenValiditySeconds(int accessTokenValiditySeconds) {
			this.accessTokenValiditySeconds = accessTokenValiditySeconds;
			return this;
		}

		public ClientBuilder refreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
			this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
			return this;
		}
```



### Bean循环依赖





## 部署过程记录







