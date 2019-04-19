package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Getter;
import me.gaigeshen.wechat.pay.AbstractResponse;

/**
 * 退款请求结果
 *
 * @author gaigeshen
 */
@Getter
public class RefundResponse extends AbstractResponse {
  private String transactionId;
  private String outTradeNo;
  private String outRefundNo;
  private String refundId;
  private Integer refundFee;
  private Integer settlementRefundFee;
  private Integer totalFee;
  private Integer settlementTotalFee;
  private String feeType;
  private Integer cashFee;
  private String cashFeeType;
  private Integer cashRefundFee;
  private Integer couponRefundFee;
  private Integer couponRefundCount;
}
