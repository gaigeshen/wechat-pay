package me.gaigeshen.wechat.pay;

import lombok.Getter;

/**
 * 下载历史交易清单请求结果，只在失败的时候才返回这些字段。正常时返回文件。
 *
 * @author gaigeshen
 */
@Getter
public class DownloadBillResponse implements Response {
  private String returnCode;
  private String returnMsg;
  private String errorCode;
}
