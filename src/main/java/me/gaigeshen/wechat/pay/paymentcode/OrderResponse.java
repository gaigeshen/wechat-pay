package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Getter;
import me.gaigeshen.wechat.pay.AbstractResponse;

/**
 * 提交付款码结果
 *
 * @author gaigeshen
 */
@Getter
public class OrderResponse extends AbstractResponse {
  private String deviceInfo;
  private String openid;
  private String isSubscribe;
  private String tradeType;
  private String bankType;
  private String feeType;
  private Integer totalFee;
  private Integer settlementTotalFee;
  private Integer couponFee;
  private String cashFeeType;
  private Integer cashFee;
  private String transactionId;
  private String outTradeNo;
  private String attach;
  private String timeEnd;
  private String promotionDetail;
}
