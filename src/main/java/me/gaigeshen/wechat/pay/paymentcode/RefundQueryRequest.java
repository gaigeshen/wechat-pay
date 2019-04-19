package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Builder;
import lombok.Getter;
import me.gaigeshen.wechat.pay.Request;

/**
 * 查询退款请求，提交退款申请后，通过调用该接口查询退款状态。退款有一定延时。
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class RefundQueryRequest implements Request<RefundQueryResponse> {
  @Override
  public Class<RefundQueryResponse> responseType() {
    return RefundQueryResponse.class;
  }
  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/pay/refundquery";
  }

  private String transactionId;
  private String outTradeNo;
  private String outRefundNo;
  private String refundId;
  private int offset;
}
