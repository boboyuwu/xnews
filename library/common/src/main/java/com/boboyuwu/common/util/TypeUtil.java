package com.boboyuwu.common.util;

import java.lang.reflect.ParameterizedType;

/**
 * Created by wubo on 2017/6/13.
 */

public class TypeUtil {

          public static <T > T getType(Object object, int index){
              try {
                  return ((Class<T>)((ParameterizedType)object.getClass().getGenericSuperclass()).getActualTypeArguments()[index]).newInstance();
              } catch (InstantiationException e) {
                  e.printStackTrace();
              } catch (IllegalAccessException e) {
                  e.printStackTrace();
              }
              return null;
          };
}
