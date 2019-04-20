package me.gaigeshen.wechat.pay;

import me.gaigeshen.wechat.pay.commons.NameUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.lang.reflect.Field;

/**
 *
 * @author gaigeshen
 */
final class ResponseBodyXmlUtils {

  /**
   * 转换响应体内容为指定的类型的对象
   *
   * @param document 响应体内容
   * @param responseType 指定类型
   * @return 指定的类型的对象
   */
  static <R> R parseToObject(Document document, Class<R> responseType) {
    R response;
    try {
      response = responseType.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IllegalStateException("Could not create new response object: " + responseType, e);
    }
    Field[] fields = FieldUtils.getAllFields(responseType);
    for (Field field : fields) {
      field.setAccessible(true);
      Element node = (Element) document.selectSingleNode("//" + NameUtils.camelToUnderline(field.getName()));
      if (node == null) {
        continue; // 可能不存在该字段
      }
      assignFieldValue(field, response, node.getTextTrim());
    }
    return response;
  }

  private static void assignFieldValue(Field field, Object response, String value) {
    Class<?> fieldType = field.getType();
    try {
      // 目前只考虑三种类型的字段
      if (fieldType == String.class) {
        field.set(response, value);
      }
      else if (ClassUtils.isAssignable(int.class, fieldType)) {
        field.set(response, Integer.valueOf(value));
      }
      else if (ClassUtils.isAssignable(long.class, fieldType)) {
        field.set(response, Long.valueOf(value));
      }
    } catch (IllegalAccessException e) {
      throw new IllegalStateException("Could not assign value to response field: " + field, e);
    }
  }
}
