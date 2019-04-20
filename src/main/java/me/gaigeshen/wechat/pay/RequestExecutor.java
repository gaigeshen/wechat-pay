package me.gaigeshen.wechat.pay;

import me.gaigeshen.wechat.pay.commons.HttpClientExecutor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.ContentResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractResponseHandler;

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

  private String parseToBody(Request<?> request) {
    RequestBodyHelper requestBodyHelper = RequestBodyHelper.create(request)
            .put("appid", config.getAppid())
            .put("mchId", config.getMchId())
            .put("nonceStr", RandomStringUtils.randomNumeric(10))
            .put("signType", "MD5");
    requestBodyHelper.put("sign", SignUtils.generate(requestBodyHelper.parametersCloned(), config.getKey()));
    return requestBodyHelper.parseToBody();
  }

  private <R> R doExecuteInternal(Request<?> request, AbstractResponseHandler<R> handler) {
    HttpPost post = new HttpPost(request.requestUri());
    post.setEntity(new StringEntity(parseToBody(request), "utf-8"));
    return executor.execute(post, handler);
  }

  /**
   * 执行请求
   *
   * @param request 请求对象
   * @param <R> 响应类型
   * @return 响应
   */
  public <R extends Response> R execute(Request<R> request) {
    HttpPost post = new HttpPost(request.requestUri());
    post.setEntity(new StringEntity(parseToBody(request), "utf-8"));

    // 响应里面并未明确指出具体的字符编码
    // 但是文档已强调为此编码
    String result = doExecuteInternal(request, new ContentResponseHandler()).asString(Charset.forName("utf-8"));

    return ResponseBodyHelper.create(result)
            .parseToObject(request.responseType());
  }

  /**
   * 执行请求，可手动处理请求结果
   *
   * @param request 请求对象
   * @param handler 请求结果处理器
   */
  public void execute(Request<?> request, ResponseBodyHandler handler) {
    Content content = doExecuteInternal(request, new ContentResponseHandler());
    handler.handle(content.getType().toString(), content.asBytes());
  }
}
