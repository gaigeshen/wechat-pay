package me.gaigeshen.wechat.pay;

import lombok.Builder;
import lombok.Getter;

/**
 * 统一下单请求
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class UnifiedOrderRequest implements Request<UnifiedOrderResponse> {
  @Override
  public Class<UnifiedOrderResponse> responseType() {
    return UnifiedOrderResponse.class;
  }
  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/pay/unifiedorder";
  }

  private String deviceInfo;
  private String body;
  private String detail;
  private String attach;
  private String outTradeNo;
  private String feeType;
  private int totalFee;
  private String spbillCreateIp;
  private String timeStart;
  private String timeExpire;
  private String goodsTag;
  private String notifyUrl;
  private String tradeType;
  private String productId;
  private String limitPay;
  private String openid;
  private String receipt;
  private String sceneInfo;
}
