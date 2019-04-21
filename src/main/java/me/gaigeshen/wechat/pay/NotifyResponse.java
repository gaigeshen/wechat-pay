package me.gaigeshen.wechat.pay;

import me.gaigeshen.wechat.pay.commons.MapBuilder;

/**
 * 通知响应
 *
 * @author gaigeshen
 */
public class NotifyResponse {

  private static final String RETURN_CODE_SUCCESS = "SUCCESS";
  private static final String RETURN_MSG_SUCCESS = "OK";

  private String returnCode;
  private String returnMsg;

  private NotifyResponse(String returnCode, String returnMsg) {
    this.returnCode = returnCode;
    this.returnMsg = returnMsg;
  }

  /**
   * 创建成功的响应
   *
   * @return 成功的响应
   */
  public static NotifyResponse create() {
    return new NotifyResponse(RETURN_CODE_SUCCESS, RETURN_MSG_SUCCESS);
  }

  /**
   * 转换为请求体
   *
   * @return 请求体
   */
  public String parseToBody() {
    return RequestBodyHelper.create(
            MapBuilder.builder(2).put("returnCode", returnCode).put("returnMsg", returnMsg).build())
            .parseToBody();
  }

  public String getReturnCode() {
    return returnCode;
  }

  public String getReturnMsg() {
    return returnMsg;
  }
}
