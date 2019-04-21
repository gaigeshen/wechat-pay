package me.gaigeshen.wechat.pay;

import lombok.Builder;
import lombok.Getter;

/**
 * 关闭订单请求
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class CloseOrderRequest implements Request<CloseOrderResponse> {
  @Override
  public Class<CloseOrderResponse> responseType() {
    return CloseOrderResponse.class;
  }
  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/pay/closeorder";
  }

  private String outTradeNo;
}
