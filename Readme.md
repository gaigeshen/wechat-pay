## 微信支付开发手册
> 使用之前建议先阅读微信官方文档

- 添加依赖，替换具体的版本号
```
<dependency>
  <groupId>me.gaigeshen.wechat</groupId>
  <artifactId>wechat-pay</artifactId>
  <version>${VERSION}</version>
</dependency>
```

- 构造`Config`实例
```
Config config = Config.builder()
    .appid("your appid")
    .mchId("your mchId")
    .key("your key")
    .secret("your secret")
    .build();
```
- 构造`RequestExecutor`实例
```
KeyStore keyStore = KeyStore.getInstance("JKS");
keyStore.load(in, "your password".toCharArray());

SSLContext sslContext = SSLContextBuilder.create().loadKeyMaterial(keyStore, "your password".toCharArray()

HttpClientExecutor httpClient = new HttpClientExecutor(2000, 2000, 3000, sslContext);
RequestExecutor executor = new RequestExecutor(httpClient, config);
```
- 使用方式样例参见：```me.gaigeshen.wechat.pay.PaymentCodeTest```
```
// 普通请求
Request req = ...;
Reaponse resp = executor.execute(req);

// 涉及下载文件的请求
Request req = ...;
ResponseBodyHandler handler = ...;
Reaponse resp = executor.execute(req, handler);
```