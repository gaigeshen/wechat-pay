package me.gaigeshen.wechat.pay.redpack;

import lombok.Getter;
import me.gaigeshen.wechat.pay.AbstractResponse;

/**
 * 摇一摇红包预下单结果
 *
 * @author gaigeshen
 */
@Getter
public class RedpackPreOrderResponse extends AbstractResponse {
  private String wxappid;
  private Integer totalAmount;
  private String spTicket;
  private String detailId;
  private String sendTime;
}
