package me.gaigeshen.wechat.pay.nativescan;

import lombok.Getter;
import me.gaigeshen.wechat.pay.AbstractResponse;

/**
 * 转换短链接请求结果
 *
 * @author gaigeshen
 */
@Getter
public class ShortUrlResponse extends AbstractResponse {
  private String shortUrl;
}
