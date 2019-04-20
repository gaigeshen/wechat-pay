package me.gaigeshen.wechat.pay;

/**
 * 请求结果处理器
 *
 * @author gaigeshen
 */
public interface ResponseBodyHandler {
  /**
   * 处理方法
   *
   * @param contentType 内容类型
   * @param content 内容
   */
  void handle(String contentType, byte[] content);
}
