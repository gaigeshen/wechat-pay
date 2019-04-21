package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Getter;
import me.gaigeshen.wechat.pay.AbstractResponse;

/**
 * 授权码获取用户标识请求结果
 *
 * @author gaigeshen
 */
@Getter
public class QueryOpenidResponse extends AbstractResponse {
  private String openid;
}
