package me.gaigeshen.wechat.pay;

/**
 * @author gaigeshen
 */
public interface Request<R extends Response> {
  Class<R> responseType();
  String requestUri();
}
