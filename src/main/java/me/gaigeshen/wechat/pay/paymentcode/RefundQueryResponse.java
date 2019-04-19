package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Getter;
import me.gaigeshen.wechat.pay.AbstractResponse;

/**
 * 查询退款结果
 *
 * @author gaigeshen
 */
@Getter
public class RefundQueryResponse extends AbstractResponse {
  private Integer totalRefundCount;
  private String transactionId;
  private String outTradeNo;
  private Integer totalFee;
  private Integer settlementTotalFee;
  private String feeType;
  private Integer cashFee;
  private Integer refundCount;
}
