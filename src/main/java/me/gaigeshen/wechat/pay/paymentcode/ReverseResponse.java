package me.gaigeshen.wechat.pay.paymentcode;

import lombok.Getter;
import me.gaigeshen.wechat.pay.AbstractResponse;

/**
 * 撤销订单结果
 *
 * @author gaigeshen
 */
@Getter
public class ReverseResponse extends AbstractResponse {
  private String recall;
}
