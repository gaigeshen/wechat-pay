package me.gaigeshen.wechat.pay;

import lombok.Builder;
import lombok.Getter;

/**
 * 下载订单评价，每次最多两百条，只能最近三个月以内的数据
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class DownloadCommentRequest implements Request<DownloadCommentResponse> {
  @Override
  public Class<DownloadCommentResponse> responseType() {
    return DownloadCommentResponse.class;
  }

  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/billcommentsp/batchquerycomment";
  }

  private String beginTime;
  private String endTime;
  private int offset;
  private int limit;
}
