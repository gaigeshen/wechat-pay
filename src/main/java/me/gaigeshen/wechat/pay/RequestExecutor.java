package me.gaigeshen.wechat.pay;

import me.gaigeshen.wechat.pay.commons.HttpClientExecutor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.nio.charset.Charset;

/**
 * 请求执行器
 *
 * @author gaigeshen
 */
public class RequestExecutor {

  private final HttpClientExecutor executor;
  private final Config config;

  /**
   * 创建请求执行器
   */
  public RequestExecutor(HttpClientExecutor executor, Config config) {
    this.executor = executor;
    this.config = config;
  }

  /**
   * 执行请求
   *
   * @param request 请求对象
   * @param <R> 响应类型
   * @return 响应
   */
  public <R extends Response> R execute(Request<R> request) {
    // 添加缺失的参数
    RequestBodyHelper requestBodyHelper = RequestBodyHelper.create(request)
            .put("appid", config.getAppid())
            .put("mchId", config.getMchId())
            .put("nonceStr", RandomStringUtils.randomNumeric(10))
            .put("signType", "MD5");

    // 添加签名
    requestBodyHelper.put("sign", SignUtils.generate(requestBodyHelper.parametersCloned(), config.getKey()));

    HttpPost post = new HttpPost(request.requestUri());
    post.setEntity(new StringEntity(requestBodyHelper.parseToBody(), "utf-8"));

    // 响应里面并未明确指出具体的字符编码
    // 但是文档已强调为此编码
    String result = executor.execute(post, Charset.forName("utf-8"));

    return ResponseBodyHelper.create(result)
            .parseToObject(request.responseType());
  }
}
