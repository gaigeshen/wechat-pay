package me.gaigeshen.wechat.pay;

import me.gaigeshen.wechat.pay.commons.NameUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求体帮助类
 *
 * @author gaigeshen
 */
final class RequestBodyHelper {
  /**
   * 请求参数集合
   */
  private final Map<String, Object> parameters;

  /**
   * 创建请求体帮助类
   *
   * @param parameters 请求参数集合
   */
  private RequestBodyHelper(Map<String, Object> parameters) {
    this.parameters = new HashMap<>(parameters);
  }

  /**
   * 创建请求体帮助类
   *
   * @param request 请求参数集合
   * @return 请求体帮助类
   */
  static RequestBodyHelper create(Request request) {
    Field[] fields = FieldUtils.getAllFields(request.getClass());
    Map<String, Object> parameters = new HashMap<>(fields.length);
    try {
      for (Field field : fields) {
        field.setAccessible(true);
        Object value = field.get(request);
        if (value != null) { // 为空的字段被忽略
          parameters.putIfAbsent(NameUtils.camelToUnderline(field.getName()), value);
        }
      }
    } catch (IllegalAccessException e) {
      throw new IllegalStateException("Could not create parameters from request", e);
    }
    return new RequestBodyHelper(parameters);
  }

  /**
   * 追加参数
   *
   * @param name 参数名称
   * @param value 参数值
   * @return 当前的请求体帮助类
   */
  RequestBodyHelper put(String name, String value) {
    if (value != null) { // 记得忽略为空的字段
      parameters.putIfAbsent(NameUtils.camelToUnderline(name), value);
    }
    return this;
  }

  /**
   * 返回不可被修改的参数映射集合
   *
   * @return 不可被修改的参数映射集合
   */
  Map<String, Object> parametersCloned() {
    return MapUtils.unmodifiableMap(parameters);
  }

  /**
   * 转换为请求体，直接拿去执行请求
   *
   * @return 请求体
   */
  String parseToBody() {
    StringBuilder result = new StringBuilder("<xml>");
    parameters.forEach((k, v) -> {
      result.append("<").append(k).append(">").append("<![CDATA[").append(v).append("]]>").append("</").append(k).append(">");
    });
    return String.valueOf(result.append("</xml>"));
  }
}
