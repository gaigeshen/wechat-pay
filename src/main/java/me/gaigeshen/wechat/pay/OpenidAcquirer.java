package me.gaigeshen.wechat.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 用于用户微信标识获取
 *
 * @author gaigeshen
 */
public class OpenidAcquirer {
  static final String GET_CODE_URI = "https://open.weixin.qq.com/connect/oauth2/authorize" +
          "?appid=APPID&redirect_uri=REDIRECT&response_type=code&scope=snsapi_base&state=#wechat_redirect";
  static final String GET_ACCESS_TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/access_token" +
          "?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

  /**
   * 构造授权链接
   *
   * @param appid 应用编号
   * @param redirectUri 重定向链接用于获取用户授权后的授权码
   * @return 授权链接给到用户
   */
  public static String buildAuthorizeUri(String appid, String redirectUri) {
    return GET_CODE_URI
            .replaceAll("APPID", appid)
            .replaceAll("REDIRECT", redirectUri);
  }

  /**
   * 获取用户的标识
   *
   * @param appid 应用编号
   * @param secret 秘钥
   * @param code 用户授权后通过重定向得到
   * @return 用户的标识，如果有异常则会抛出
   */
  public static String obtainOpenid(String appid, String secret, String code) {
    String uri = GET_ACCESS_TOKEN_URI
            .replaceAll("APPID", appid)
            .replaceAll("SECRET", secret)
            .replaceAll("CODE", code);
    String result = null;
    try {
      result = Request.Get(uri).connectTimeout(2000).socketTimeout(3000).execute().returnContent().asString(Charset.forName("utf-8"));
    } catch (IOException e) {
      throw new IllegalStateException("Could not obtain openid", e);
    }
    JSONObject jsonResult = JSON.parseObject(result);
    int errcode = jsonResult.getIntValue("errcode");
    if (errcode > 0) {
      throw new IllegalStateException("Could not obtain openid, result: " + result);
    }
    return jsonResult.getString("openid");
  }
}
