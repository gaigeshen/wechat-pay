package me.gaigeshen.wechat.pay;

import me.gaigeshen.wechat.pay.commons.NameUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.lang.reflect.Field;

/**
 * 响应体帮助类
 *
 * @author gaigeshen
 */
final class ResponseBodyHelper {
  private final String coontent;
  private final Document document;
  private ResponseBodyHelper(String coontent) throws DocumentException {
    this.coontent = coontent;
    this.document = new SAXReader().read(new StringReader(this.coontent));
  }
  static ResponseBodyHelper create(String bodyContent) {
    try {
      return new ResponseBodyHelper(bodyContent);
    } catch (DocumentException e) {
      throw new IllegalStateException("Could not create response body helper", e);
    }
  }
  <R> R parseToObject(Class<R> responseType) {
    R response;
    try {
      response = responseType.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IllegalStateException(
              "Could not create new response object: " + responseType, e);
    }
    Field[] fields = FieldUtils.getAllFields(responseType);
    for (Field field : fields) {
      field.setAccessible(true);
      Element node = (Element) document.selectSingleNode("//" + NameUtils.camelToUnderline(field.getName()));
      if (node == null) {
        continue; // 可能不存在该字段
      }
      String value = node.getTextTrim();
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
    return response;
  }

}
