package com.base.atlas.common.util;

import com.vdurmont.emoji.EmojiParser;
import com.base.atlas.common.annotation.EmojiTransferField;

import java.lang.reflect.Field;

/**
 * @author CaiJie Pang
 * @since 2023/2/5
 */
public class EmojiTransfer {

  public static void parseObjectToAlias(Object source) {
    if (source == null) {
      return;
    }

    Field[] fields = source.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (!"java.lang.String".equals(field.getGenericType().getTypeName())) {
        continue;
      }

      EmojiTransferField annotation = field.getAnnotation(EmojiTransferField.class);
      if (annotation == null || !annotation.forPersist()) {
        continue;
      }

      boolean fieldAccessible = field.isAccessible();
      if (!fieldAccessible) {
        field.setAccessible(true);
      }

      try {
        String orgValue = (String) field.get(source);
        if (orgValue == null) {
          continue;
        }
        field.set(source, EmojiParser.parseToAliases(orgValue));
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static void parseObjectToUnicode(Object source) {
    if (source == null) {
      return;
    }

    Field[] fields = source.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (!"java.lang.String".equals(field.getGenericType().getTypeName())) {
        continue;
      }

      EmojiTransferField annotation = field.getAnnotation(EmojiTransferField.class);
      if (annotation == null || !annotation.forView()) {
        continue;
      }

      boolean fieldAccessible = field.isAccessible();
      if (!fieldAccessible) {
        field.setAccessible(true);
      }

      try {
        String orgValue = (String) field.get(source);
        field.set(source, EmojiParser.parseToUnicode(orgValue));
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static String parseToAlias(String source) {
    if (StringUtils.isBlank(source)) {
      return null;
    }
    return EmojiParser.parseToAliases(source);
  }

  public static String parseToUnicode(String source) {
    if (StringUtils.isBlank(source)) {
      return null;
    }
    return EmojiParser.parseToUnicode(source);
  }

}
