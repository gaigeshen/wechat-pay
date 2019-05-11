package me.gaigeshen.wechat.pay.redpack;

import lombok.Builder;
import lombok.Getter;
import me.gaigeshen.wechat.pay.Request;

/**
 * 摇一摇红包预下单
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class RedpackPreOrderRequest implements Request<RedpackPreOrderResponse> {
  private String mchBillno;
  private String sendName;
  private String hbType;
  private int totalAmount;
  private int totalNum;
  private String amtType;
  private String wishing;
  private String actName;
  private String remark;
  private String authMchid;
  private String authAppid;
  private String riskCntl;

  @Override
  public Class<RedpackPreOrderResponse> responseType() {
    return RedpackPreOrderResponse.class;
  }
  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/mmpaymkttransfers/hbpreorder";
  }
}
