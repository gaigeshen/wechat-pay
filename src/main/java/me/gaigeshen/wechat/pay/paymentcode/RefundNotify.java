package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Getter;

/**
 * 退款结果通知
 *
 * @author gaigeshen
 */
@Getter
public class RefundNotify {
  private String transactionId;
  private String outTradeNo;
  private String refundId;
  private String outRefundNo;
  private Integer totalFee;
  private Integer settlementTotalFee;
  private Integer refundFee;
  private Integer settlementRefundFee;
  private String refundStatus;
  private String successTime;
  private String refundRecvAccout;
  private String refundAccount;
  private String refundRequestSource;
}
