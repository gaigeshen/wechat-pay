package me.gaigeshen.wechat.pay;

import lombok.Builder;
import lombok.Getter;

/**
 * 查询订单请求
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class QueryRequest implements Request<QueryResponse> {
  @Override
  public Class<QueryResponse> responseType() {
    return QueryResponse.class;
  }
  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/pay/orderquery";
  }

  private String transactionId;
  private String outTradeNo;
}
