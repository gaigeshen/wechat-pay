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
  private final Map<String, Object> parameters;
  private RequestBodyHelper(Map<String, Object> parameters) {
    this.parameters = new HashMap<>(parameters);
  }
  static RequestBodyHelper create(Request request) {
    Field[] fields = FieldUtils.getAllFields(request.getClass());
    Map<String, Object> parameters = new HashMap<>(fields.length);
    try {
      for (Field field : fields) {
        field.setAccessible(true);
        Object value = field.get(request);
        if (value != null) {
          parameters.putIfAbsent(NameUtils.camelToUnderline(field.getName()), value);
        }
      }
    } catch (IllegalAccessException e) {
      throw new IllegalStateException("Could not create parameters from request", e);
    }
    return new RequestBodyHelper(parameters);
  }
  RequestBodyHelper put(String name, String value) {
    if (value != null) {
      parameters.putIfAbsent(NameUtils.camelToUnderline(name), value);
    }
    return this;
  }
  Map<String, Object> parametersCloned() {
    return MapUtils.unmodifiableMap(parameters);
  }
  String parseToBody() {
    StringBuilder result = new StringBuilder("<xml>");
    parameters.forEach((k, v) -> {
      result.append("<").append(k).append(">").append("<![CDATA[").append(v).append("]]>").append("</").append(k).append(">");
    });
    return String.valueOf(result.append("</xml>"));
  }
}
