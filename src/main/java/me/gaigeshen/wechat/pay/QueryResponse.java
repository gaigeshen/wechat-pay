package me.gaigeshen.wechat.pay;

import lombok.Getter;

/**
 * 查询订单结果
 *
 * @author gaigeshen
 */
@Getter
public class QueryResponse extends AbstractResponse {
  private String deviceInfo;
  private String openid;
  private String isSubscribe;
  private String tradeType;
  private String tradeState;
  private String bankType;
  private Integer totalFee;
  private Integer settlementTotalFee;
  private String feeType;
  private Integer cashFee;
  private String cashFeeType;
  private Integer couponFee;
  private Integer couponCount;
  private String transactionId;
  private String outTradeNo;
  private String attach;
  private String timeEnd;
  private String tradeStateDesc;
}
