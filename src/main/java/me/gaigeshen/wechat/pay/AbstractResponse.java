package me.gaigeshen.wechat.pay;

import lombok.Getter;

/**
 * 抽象请求结果，在此处的字段不需要在子类再次申明，子类尽量关注具体的业务字段
 *
 * @author gaigeshen
 */
@Getter
public abstract class AbstractResponse implements Response {
  private static final String RETURN_CODE_SUCCESS = "SUCCESS";
  private static final String RETURN_CODE_FAIL = "FAIL";
  private static final String RESULT_CODE_SUCCESS = "SUCCESS";
  private static final String RESULT_CODE_FAIL = "FAIL";

  private String returnCode;  // 返回状态码，此字段是通信标识，非交易标识
  private String returnMsg;   // 返回信息，当返回状态码为失败时为错误原因
  private String resultCode;  // 业务结果
  private String errCode;     // 错误代码
  private String errCodeDes;  // 错误代码描述
  private String appid;
  private String mchId;
  private String nonceStr;
  private String sign;

  /**
   * 返回是否通信成功
   *
   * @return 是否通信成功
   */
  public boolean isReturnSucceeded() {
    return RETURN_CODE_SUCCESS.equals(returnCode);
  }

  /**
   * 返回是否业务成功
   *
   * @return 是否业务成功
   */
  public boolean isResultSucceeded() {
    return RESULT_CODE_SUCCESS.equals(resultCode);
  }
}
