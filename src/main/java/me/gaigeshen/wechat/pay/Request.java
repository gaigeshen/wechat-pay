package me.gaigeshen.wechat.pay;

/**
 * 请求
 *
 * @author gaigeshen
 */
public interface Request<R extends Response> {
  /**
   * 返回请求结果类型
   *
   * @return 请求结果类型
   */
  Class<R> responseType();

  /**
   * 返回请求接口地址
   *
   * @return 请求接口地址
   */
  String requestUri();
}
