package me.gaigeshen.wechat.pay.commons;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @author gaigeshen
 */
public final class JsonUtils {

  private JsonUtils() {}
  
  public static String toJson(Object data) {
    return JSON.toJSONString(data, SerializerFeature.WriteMapNullValue);
  }
  
  public static <T> T fromJson(String json, Class<T> type) {
    return JSON.parseObject(json, type);
  }
  
}
