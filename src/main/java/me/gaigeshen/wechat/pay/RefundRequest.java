package me.gaigeshen.wechat.pay;

import lombok.Builder;
import lombok.Getter;

/**
 * <p>
 * 退款请求，交易时间超过一年的订单无法提交退款<br>
 * 微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。<br>
 * 一笔退款失败后重新提交，请不要更换退款单号，请使用原商户退款单号。<br>
 * 请求频率限制：150qps，错误或无效请求频率限制：6qps<br>
 * 每个支付订单的部分退款次数不能超过50次
 * </p>
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class RefundRequest implements Request<RefundResponse> {
  @Override
  public Class<RefundResponse> responseType() {
    return RefundResponse.class;
  }

  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/secapi/pay/refund";
  }

  private String transactionId;
  private String outTradeNo;
  private String outRefundNo;
  private int totalFee;
  private int refundFee;
  private String refundFeeType;
  private String refundDesc;
  private String refundAccount;
  private String notifyUrl;

}
