## 微信支付开发手册

[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/gaigeshen/wechat-pay.svg?branch=develop)](https://travis-ci.org/gaigeshen/wechat-pay)
[![Maven Central](https://img.shields.io/maven-central/v/me.gaigeshen.wechat/wechat-pay.svg)](http://mvnrepository.com/artifact/me.gaigeshen.wechat/wechat-pay)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/me.gaigeshen.wechat/wechat-pay.svg)](https://oss.sonatype.org/content/repositories/snapshots/me/gaigeshen/wechat/wechat-pay)
[![GitHub last commit](https://img.shields.io/github/last-commit/gaigeshen/wechat-pay.svg)](https://github.com/gaigeshen/wechat-pay/commits)
### 添加依赖

```xml
<dependency>
  <groupId>me.gaigeshen.wechat</groupId>
  <artifactId>wechat-pay</artifactId>
  <version>${VERSION}</version>
</dependency>
```

### 构造配置实例

```java
Config config = Config.builder()
    .appid("your appid")
    .mchId("your mchId")
    .key("your key")
    .secret("your secret")
    .build();
```
### 构造请求执行器实例

```java
InputStream in = ...;
KeyStore keyStore = KeyStore.getInstance("JKS");
keyStore.load(in, "your password".toCharArray());

SSLContext sslContext = SSLContextBuilder.create().loadKeyMaterial(keyStore, "your password".toCharArray()

HttpClientExecutor httpClient = new HttpClientExecutor(2000, 2000, 3000, sslContext);
RequestExecutor executor = new RequestExecutor(httpClient, config);

// 在必要的时候关闭
executor.close();
```
### 使用方式

```java
// 普通请求
Request req = ...;
Response resp = executor.execute(req);

// 涉及下载文件的请求
Request req = ...;
ResponseBodyHandler handler = ...;
Response resp = executor.execute(req, handler);
```