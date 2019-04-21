package me.gaigeshen.wechat.pay;

import lombok.Getter;
import me.gaigeshen.wechat.pay.Response;

/**
 * 下载订单评价请求结果
 *
 * @author gaigeshen
 */
@Getter
public class DownloadCommentResponse implements Response {
  private String returnCode;
  private String returnMsg;
  private String resultCode;
  private String errCode;
  private String errCodeDes;
}
