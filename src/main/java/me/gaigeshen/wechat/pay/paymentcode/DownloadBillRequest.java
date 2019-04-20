package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Builder;
import lombok.Getter;
import me.gaigeshen.wechat.pay.Request;

/**
 * 下载历史交易清单
 * <p>
 *   微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致。<br>
 *   微信在次日九点启动生成前一天的对账单，建议商户十点后再获取。涉及的金额单位为元。<br>
 *   只能下载三个月以内的账单<br>
 * </p>
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class DownloadBillRequest implements Request<DownloadBillResponse> {
  @Override
  public Class<DownloadBillResponse> responseType() {
    return DownloadBillResponse.class;
  }

  @Override
  public String requestUri() {
    return "https://api.mch.weixin.qq.com/pay/downloadbill";
  }

  private String billDate;
  private String billType;
  private String tarType;
}
