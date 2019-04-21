package me.gaigeshen.wechat.pay.nativescan;

import lombok.Builder;
import lombok.Getter;
import me.gaigeshen.wechat.pay.Request;

/**
 * 转换短链接请求
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class ShortUrlRequest implements Request<ShortUrlResponse> {
  @Override
  public Class<ShortUrlResponse> responseType() {
    return ShortUrlResponse.class;
  }
  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/tools/shorturl";
  }

  private String longUrl;
}
