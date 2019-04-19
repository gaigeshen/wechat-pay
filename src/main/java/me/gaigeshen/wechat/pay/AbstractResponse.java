package me.gaigeshen.wechat.pay;

import lombok.Getter;

/**
 * @author gaigeshen
 */
@Getter
public abstract class AbstractResponse implements Response {
  private String returnCode;
  private String returnMsg;
  private String appid;
  private String mchId;
  private String nonceStr;
  private String sign;
  private String resultCode;
  private String errCode;
  private String errCodeDes;
}
