package me.gaigeshen.wechat.pay;

import lombok.Getter;

/**
 * 统一下单请求结果
 *
 * @author gaigeshen
 */
@Getter
public class UnifiedOrderResponse extends AbstractResponse {
  private String deviceInfo;
  private String tradeType;
  private String prepayId;
  private String codeUrl;
  private String mwebUrl;
}
