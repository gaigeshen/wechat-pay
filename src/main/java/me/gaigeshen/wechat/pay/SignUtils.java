package me.gaigeshen.wechat.pay;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 签名工具类
 *
 * @author gaigeshen
 */
public final class SignUtils {

  /**
   * 检查签名是否正确
   *
   * @param parameters 参数集合不能为空对象或者空集合
   * @param key 秘钥
   * @param signValue 需要检查的签名
   * @return 返回是否正确
   */
  public static boolean check(Map<String, Object> parameters, String key, String signValue) {
    return generate(parameters, key).equals(signValue);
  }

  /**
   * 生成签名
   *
   * @param parameters 参数集合不能为空对象或者空集合
   * @param key 秘钥
   * @return 签名结果
   */
  public static String generate(Map<String, Object> parameters, String key) {
    if (parameters == null || parameters.size() < 1) {
      throw new IllegalArgumentException("parameters is null or empty");
    }

    StringBuilder keyvalues = new StringBuilder();
    parameters.keySet().stream().sorted(String::compareTo).forEachOrdered(p -> {
      if (StringUtils.isNotBlank(p)) {
        keyvalues.append("&").append(p).append("=").append(parameters.get(p));
      }
    });

    keyvalues.append("&key=").append(key);
    return DigestUtils.md5Hex(keyvalues.substring(1)).toUpperCase();
  }
}
