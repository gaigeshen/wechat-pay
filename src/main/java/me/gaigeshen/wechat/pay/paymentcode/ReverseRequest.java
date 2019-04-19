package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Builder;
import lombok.Getter;
import me.gaigeshen.wechat.pay.Request;

/**
 * <p>
 * 撤销订单请求，需要证书。支付交易返回失败或支付系统超时，调用该接口撤销交易。<br>
 * 如果此订单用户支付失败，微信支付系统会将此订单关闭；如果用户支付成功，微信支付系统会将此订单资金退还给用户。<br>
 * 七天以内的交易单可调用撤销，其他正常支付的订单如需实现相同功能请调用申请退款。
 * </p>
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class ReverseRequest implements Request<ReverseResponse> {
  @Override
  public Class<ReverseResponse> responseType() {
    return ReverseResponse.class;
  }
  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/secapi/pay/reverse";
  }

  private String transactionId;
  private String outTradeNo;
}
