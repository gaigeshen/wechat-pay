package me.gaigeshen.wechat.pay;

import lombok.Builder;
import lombok.Getter;

/**
 * 配置
 *
 * @author gaigeshen
 */
@Getter
@Builder
public class Config {
  private String appid;
  private String mchId;
  private String key;
  private String secret;
}
