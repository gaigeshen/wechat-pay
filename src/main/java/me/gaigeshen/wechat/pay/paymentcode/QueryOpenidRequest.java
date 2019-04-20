package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Builder;
import lombok.Getter;
import me.gaigeshen.wechat.pay.Request;

/**
 * 授权码获取用户标识，调用查询后，该授权码只能由此商户号发起扣款，直至授权码更新。
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class QueryOpenidRequest implements Request<QueryOpenidResponse> {
  @Override
  public Class<QueryOpenidResponse> responseType() {
    return QueryOpenidResponse.class;
  }
  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/tools/authcodetoopenid";
  }
  private String authCode;
}
