package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Builder;
import lombok.Getter;
import me.gaigeshen.wechat.pay.Request;

/**
 * 提交付款码请求
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class OrderRequest implements Request<OrderResponse> {

  @Override
  public Class<OrderResponse> responseType() {
    return OrderResponse.class;
  }

  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/pay/micropay";
  }

  private String deviceInfo;
  private String body;
  private String detail;
  private String attach;
  private String outTradeNo;
  private int totalFee;
  private String feeType;
  private String spbillCreateIp;
  private String goodsTag;
  private String limitPay;
  private String timeStart;
  private String timeExpire;
  private String receipt;
  private String authCode;
  private String sceneInfo;
}
