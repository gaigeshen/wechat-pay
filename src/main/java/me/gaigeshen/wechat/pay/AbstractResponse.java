package me.gaigeshen.wechat.pay;

import lombok.Getter;

/**
 * 抽象请求结果，在此处的字段不需要在子类再次申明，子类尽量关注具体的业务字段
 *
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
